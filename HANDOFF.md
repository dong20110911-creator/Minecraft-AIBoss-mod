# HANDOFF

- Last task: M0-01. Code commit `05f309e`. Build, 3 GameTests, dedicated-server smoke and a client world join with screenshots all passed in the sandbox. See PROGRESS.md for the exact list and the open items.
- M0-01 stays OPEN until the "Not verified / open" items in PROGRESS.md are accepted or done (notably: real server+client connection, in-game Curios UI, recipe check).
- Environment recipe (cloud sandbox): JDK 17 via apt (`openjdk-17-jdk-headless`), `xvfb xdotool imagemagick x11-utils` for client checks, network allowlist for the Forge/Mojang/GeckoLib/Curios/Parchment hosts.
- Commands:
  - build: `./gradlew build --no-daemon`
  - tests: `./gradlew runGameTestServer --no-daemon` (look for `All 3 required tests passed`)
  - server smoke: `scripts/server-smoke.sh logs/server.log`
  - client: `scripts/make-client-world.sh`, set `allowCommands=1` in `run/saves/m0flat/level.dat` (nbtlib), then
    `DISPLAY=:99 LIBGL_ALWAYS_SOFTWARE=1 ./gradlew runClient -Pquickplay=m0flat` under `Xvfb :99 -screen 0 1280x720x24`.
    Do not press Escape while capturing animation: single-player pauses the game.
- Next step after sign-off: M0 closes, then M1 (combat base) per docs/00_design_v0.3.md section 31. Do not start it without a new CURRENT_TASK.md.
