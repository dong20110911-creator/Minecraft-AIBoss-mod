# DECISIONS
- 2026-11-14: Design baseline = v0.3 + v0.4 addendum (v0.4 §12 lists overrides). Docs live in `docs/`.
- 2026-11-14: Mod id `ai_encounters`, package `com.aiencounters`.
- 2026-11-14: Curios slot data under `data/ai_encounters/curios/` per design §19; to be verified at first run.
- 2026-11-14: Kept Forge 47.3.0 / GeckoLib 4.4.9 / Curios 5.9.1+1.20.1 unchanged; all resolved and ran. No upgrades.
- 2026-11-14: GameTest classes live in src/main (package `com.aiencounters.gametest`); `forge.enabledGameTestNamespaces` is set only for dev runs.
- 2026-11-14: No GitHub Actions workflow added: the sandbox build works, so the fallback was not needed.
