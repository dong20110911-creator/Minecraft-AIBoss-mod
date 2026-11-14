# VERSION_LOCK

Verified 2026-11-14: `./gradlew clean build`, `runGameTestServer` (3/3), dedicated-server smoke, and dev client (joined world) all ran on this set.

| Component | Version / file | SHA-256 | Status |
| --- | --- | --- | --- |
| Minecraft | 1.20.1 | n/a | VERIFIED (build + server + client ran) |
| Forge | 1.20.1-47.3.0 (`forge-1.20.1-47.3.0-universal.jar`) | a3c07e6b33fe8a4b42fdb4841a37e3ffda62797366c7050c481d799d6d35831c | VERIFIED |
| ForgeGradle | [6.0,6.2) resolved by Gradle plugin portal / Forge maven | not recorded | VERIFIED (build ran) |
| MCP config | 1.20.1-20230612.114412, official mappings 1.20.1 | not recorded | VERIFIED |
| GeckoLib | `geckolib-forge-1.20.1-4.4.9.jar` (dl.cloudsmith.io/public/geckolib3/geckolib/maven) | b6b2f421daf4e87d3bc6011f734310c6dd46988ba18f9cbb9737f5ddb531d6df | VERIFIED (entity renders and animates) |
| Curios | `curios-forge-5.9.1+1.20.1.jar` (maven.theillusivec4.top) | 76cc4a9154f439fa52613cd81197161ba714e63d071bae5537d41ce5a09222d1 | VERIFIED (slot loads, bound to player) |
| Gradle | 8.1.1 via wrapper | n/a | VERIFIED |
| Java | OpenJDK 17.0.21 (Ubuntu apt `openjdk-17-jdk-headless`); Gradle's own JVM is 17 | n/a | VERIFIED |
| Better Combat / Combat Roll / Epic Fight | not integrated | n/a | NOT_RUN |

Test environment: Ubuntu 24.04, 4 vCPU, 15 GB RAM, no GPU (Xvfb + Mesa llvmpipe software OpenGL), no audio device.

Gradle must run on JDK 17: `./gradlew --version` must print `JVM: 17.x`. In this sandbox `update-alternatives` selects 17;
elsewhere set `JAVA_HOME`.

Dev-only note: Curios needs the Mixin ref-map remap properties in the `runs` block of `build.gradle`, otherwise the server crashes at start in the dev environment.
