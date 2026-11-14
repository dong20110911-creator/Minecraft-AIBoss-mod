# PROGRESS

State: **M0-01 acceptance: build PASS, 3 GameTests PASS, dedicated server PASS, client entered world with screenshots.
M0-01 is NOT closed**: the rows under "Not verified" below are still open (see end of file).

Code under test: commit `05f309e` (docs-only commits follow it). Verified 2026-11-14 in the cloud sandbox,
Ubuntu 24.04, JDK 17.0.21 (Gradle 8.1.1 JVM confirmed as 17 via `./gradlew --version`).

| Check | Result | Evidence |
| --- | --- | --- |
| Dependencies resolve (Forge 47.3.0, GeckoLib 4.4.9, Curios 5.9.1+1.20.1) | PASS | `verification/logs/final-build.log`; hashes in VERSION_LOCK.md |
| `./gradlew clean build` | PASS (BUILD SUCCESSFUL, no compiler warnings printed) | `verification/logs/final-build.log`; jar `build/libs/ai_encounters-0.0.1-m0.jar` (18114 bytes; hash changes per build because of timestamps) |
| Automated tests (Forge GameTest, `./gradlew runGameTestServer`) | **3 run, 3 passed, 0 failed** | `verification/logs/final-gametest.log` |
| Dedicated server start/stop (`scripts/server-smoke.sh`) | PASS: `Done (5.1s)`, then `stop`, worlds saved, Gradle exit 0 | `verification/logs/final-server.log` |
| Server-side isolation | PASS for this scope: server loads the mod with `ClientSetup` (Dist.CLIENT) present, no class-loading error | same log; only static dependency path checked by running, no extra static analysis |
| Client process start | PASS | `verification/logs/client-2.log` |
| Client entered a world | PASS: `Dev joined the game` in world `m0flat` (integrated server, Xvfb + Mesa software GL) | `verification/logs/client-2.log`, `verification/screenshots/` |
| test_dummy rendered | PASS (seen): blue box model with texture | `01-dummy-front.png`, `02-dummy-idle-later.png` |
| test_dummy idle animation | PASS (seen): head turn between frames; pixel difference vs frame 0 is nonzero, returns to 0 after ~1.05 s, loop length 2.0 s | `05-idle-animation-extremes.png`; the numbers came from an ad-hoc capture burst, the burst frames are not committed |
| Traveler's Sword model/texture | PASS (seen): given via `/give`, held item renders, no missing-texture pink/black | `01-dummy-front.png` |
| Curios slot loaded in client | PASS: log `Loaded 11 curio slots` (10 built-in + ours) and 1 entity binding | `client-2.log` |

## GameTests (the only automated tests; all in `M0GameTests.java`)
1. `travelerSwordFinalAttributes`: player attribute values after applying the sword's main-hand modifiers: damage 6.0, speed 1.6 (tolerance 1e-6). PASS.
2. `testDummySpawnsAndSurvives`: spawns test_dummy, after 40 ticks still alive, full health, found by UUID. PASS.
3. `curiosCharmSlotLoadedForPlayers`: slot `ai_encounters_charm` exists with size 1 and is bound to the player entity. PASS.

Mutation check (not part of the suite): with attack speed changed to 2.0 and the slot JSON removed, tests 1 and 3 failed with
the expected messages (`verification/logs/gametest-mutation.log`); both were restored, `git diff` clean afterwards.
Test 2 was not mutation-checked.

## Fixes made while getting the run to work
- Curios mixins reference SRG names; in the dev environment this crashed server start (`InvalidAccessorException ... f_19803_`).
  Fixed by `mixin.env.remapRefMap` + `mixin.env.refMapRemappingFile` in the Gradle run configs.
- Gradle did not forward stdin to the dev server, so `stop` never arrived (server kept running). Fixed with `standardInput = System.in`.
- GameTest template name: Forge adds the namespace itself, so the template is `empty` (own `empty.nbt` structure).
- Test world needed `allowCommands=1` in `level.dat` for the integrated-server commands (test-harness only, not a mod issue).

## Not verified / open
- Music/sound: sandbox has no audio device (`SoundEngine: Error starting SoundSystem`). Nothing in M0 plays sound.
- Placeholder art only (flat-colour texture, box model). The model looks like a plain blue pillar; no art review done.
- Curios slot UI not opened in-game; only the data loading is proven (log + GameTest). Slot has no icon and no tagged items yet.
- Sword crafting recipe not exercised in-game (only 7 recipes loaded: seen in log; recipe correctness unproven).
- Two-player / multiplayer, `/summon` by a normal player on a dedicated server with a real client: NOT_RUN.
- Real dedicated server with a real client connecting: NOT_RUN (client used the integrated server).
- Automated scripted client test: client checks were driven by xdotool by hand, not a repeatable test; `scripts/make-client-world.sh` only prepares the world.
- GitHub Actions workflow: not added, not needed because the sandbox build works. No CI exists.
- Anything beyond M0 (HitResolver, Boss, combat base) is untouched and is DESIGN_ONLY.
