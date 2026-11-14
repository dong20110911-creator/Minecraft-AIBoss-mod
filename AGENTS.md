# AGENTS.md — 调用者 · 协议裂隙 (ai_encounters)

Minecraft Java 1.20.1 / Forge / Java 17 mod. Mod id `ai_encounters`, package `com.aiencounters`.

## Read first
1. `CURRENT_TASK.md` — the ONE task you may work on.
2. `PROGRESS.md`, `HANDOFF.md` — real state; disk and git beat memory.
3. `VERSION_LOCK.md` — never change MC version, loader, or libs without a DECISIONS entry.
4. `docs/00_design_v0.3.md` (base spec) and `docs/01_vfx_combat_addendum_v0.4.md` (overrides listed in its §12).

## Rules
- One task at a time; do not implement five bosses at once or touch boss mechanics before M2.
- Server-authoritative damage: single `HitResolver` entry; animation callbacks never deal damage.
- Common code must not import client classes (`net.minecraft.client.*`, renderers). Client code lives in `com.aiencounters.client`.
- Optional mods (Better Combat, Combat Roll, Epic Fight) only via isolated compat entry points.
- Report honestly: PASS / FAIL / NOT_RUN. Never write a pass you did not observe.
- Preserve user changes; no destructive git ops. Update PROGRESS/HANDOFF at the end of each task.
