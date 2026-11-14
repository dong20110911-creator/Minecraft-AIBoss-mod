# HANDOFF

- Last task: M0-01 scaffold. Nothing compiled yet.
- Next step on a machine with JDK 17 and network access: run `./gradlew build` (wrapper is Gradle 8.1.1, generated but never run against this project), fix first errors, then `runClient` / `runServer`.
- Likely trouble spots: dependency versions in `gradle.properties` (from memory); GeckoLib 4 API names (`GeoEntity`, `DefaultedEntityGeoModel`); Curios slot JSON path (`data/ai_encounters/curios/...` per design §19, verify against Curios 5.9 docs); recipe uses 7 ingredients in a shaped 3x3.
- Placeholder art: flat-colour textures and a box model; replace in later tasks.
