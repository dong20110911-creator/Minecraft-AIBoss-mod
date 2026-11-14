#!/usr/bin/env bash
# Starts the dev dedicated server, waits for "Done (", sends "stop", and fails on
# timeout, crash, FATAL/ERROR lines, or non-zero Gradle exit. Usage: scripts/server-smoke.sh [logfile]
set -u
LOG="${1:-logs/server-smoke.log}"
mkdir -p logs run
echo "eula=true" > run/eula.txt
printf 'online-mode=false\nlevel-name=m0world\nlevel-seed=1\nspawn-protection=0\n' > run/server.properties
FIFO="$(mktemp -u)"; mkfifo "$FIFO"
: > "$LOG"
( ./gradlew runServer --no-daemon < "$FIFO" > "$LOG" 2>&1; echo "gradle_exit=$?" >> "$LOG" ) &
exec 3> "$FIFO"
DONE=0
for _ in $(seq 1 200); do
  if grep -q 'Done (' "$LOG"; then DONE=1; break; fi
  if grep -q '^gradle_exit=' "$LOG"; then break; fi
  sleep 3
done
if [ "$DONE" = 1 ]; then
  sleep 5; echo "stop" >&3
  for _ in $(seq 1 40); do grep -q '^gradle_exit=' "$LOG" && break; sleep 2; done
fi
exec 3>&-; rm -f "$FIFO"
EXIT="$(grep '^gradle_exit=' "$LOG" | tail -1 | cut -d= -f2)"
BAD="$(grep -E '/(FATAL|ERROR)\]' "$LOG" | grep -vc 'YggdrasilServicesKeyInfo')"
echo "done_seen=$DONE gradle_exit=${EXIT:-none} error_lines=$BAD"
[ "$DONE" = 1 ] && [ "${EXIT:-1}" = 0 ] && [ "$BAD" = 0 ]
