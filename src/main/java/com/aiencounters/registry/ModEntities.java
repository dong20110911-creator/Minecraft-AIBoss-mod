package com.aiencounters.registry;

import com.aiencounters.AiEncounters;
import com.aiencounters.entity.TestDummyEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AiEncounters.MOD_ID);

    /** M0 test entity: harmless, no AI goals, only plays a GeckoLib idle loop. */
    public static final RegistryObject<EntityType<TestDummyEntity>> TEST_DUMMY = ENTITIES.register("test_dummy",
            () -> EntityType.Builder.of(TestDummyEntity::new, MobCategory.MISC)
                    .sized(0.6f, 1.95f)
                    .clientTrackingRange(8)
                    .build("test_dummy"));

    public static void onAttributes(EntityAttributeCreationEvent event) {
        event.put(TEST_DUMMY.get(), TestDummyEntity.createAttributes().build());
    }

    private ModEntities() {}
}
