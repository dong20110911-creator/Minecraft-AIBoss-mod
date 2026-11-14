#!/usr/bin/env bash
# Builds run/saves/m0flat: a peaceful flat world with one ai_encounters:test_dummy 3 blocks south of spawn.
set -eu
LOG="${1:-logs/make-world.log}"; mkdir -p logs run
rm -rf run/m0flat run/saves/m0flat
echo "eula=true" > run/eula.txt
printf 'online-mode=false\nlevel-name=m0flat\nlevel-type=minecraft\\:flat\ndifficulty=peaceful\ngamemode=creative\nspawn-protection=0\nspawn-monsters=false\nspawn-animals=false\n' > run/server.properties
FIFO="$(mktemp -u)"; mkfifo "$FIFO"; : > "$LOG"
( ./gradlew runServer --no-daemon < "$FIFO" > "$LOG" 2>&1; echo "gradle_exit=$?" >> "$LOG" ) &
exec 3> "$FIFO"
for _ in $(seq 1 200); do grep -q 'Done (' "$LOG" && break; sleep 3; done
grep -q 'Done (' "$LOG" || { echo "server did not start"; exit 1; }
sleep 3
for c in "setworldspawn 0 -60 0" "summon ai_encounters:test_dummy 0.5 -60 3.5" "save-all flush"; do echo "$c" >&3; sleep 3; done
echo stop >&3
for _ in $(seq 1 40); do grep -q '^gradle_exit=' "$LOG" && break; sleep 2; done
exec 3>&-; rm -f "$FIFO"
mkdir -p run/saves && cp -r run/m0flat run/saves/m0flat
grep -E "Summoned|Set the world spawn" "$LOG" | cut -c1-160
grep -q '^gradle_exit=0' "$LOG"
