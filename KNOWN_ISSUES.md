# KNOWN_ISSUES

- Sandbox only: no audio device, so `SoundEngine: Error starting SoundSystem` appears in client logs. Not a mod issue.
- Sandbox only: `YggdrasilServicesKeyInfo` ERROR at start (cannot reach api.minecraftservices.com). `scripts/server-smoke.sh` ignores exactly this line.
- Deprecation notice from Gradle 8.1.1 "incompatible with Gradle 9.0" comes from ForgeGradle; harmless for now.
- test_dummy art is a placeholder pillar; sword texture is a flat grey square.
- Curios slot has no icon (default empty-slot art) and no item tag, so no item can be put in it yet.
- The mod jar hash differs between builds (timestamps); do not use it as a lock value.
- Client verification is manual (xdotool); there is no repeatable automated client test.
