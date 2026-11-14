# CURRENT_TASK — M0-01

**Goal:** minimal Forge 1.20.1 project that builds and starts.
**Scope:** Gradle/Forge project, `VERSION_LOCK.md`, one item (`traveler_sword`), one test entity (`test_dummy`) with a GeckoLib idle animation, one Curios slot (`ai_encounters_charm`).
**Done when (three checks, each recorded in PROGRESS.md):**
1. `./gradlew build` succeeds.
2. Dev client starts; `/summon ai_encounters:test_dummy` shows an idling model; Curios shows the charm slot.
3. Dev dedicated server starts with no client-class loading crash.
**Allowed to change:** build files, `src/main/**`, root docs.
**Not allowed:** boss mechanics, combat system, dependency upgrades without DECISIONS entry.
