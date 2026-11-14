package com.aiencounters.client;

import com.aiencounters.AiEncounters;
import com.aiencounters.entity.TestDummyEntity;
import com.aiencounters.registry.ModEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/** Client-only. Never referenced from common code, so a dedicated server never loads it. */
@Mod.EventBusSubscriber(modid = AiEncounters.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientSetup {
    @SubscribeEvent
    public static void onRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.TEST_DUMMY.get(), ctx ->
                new GeoEntityRenderer<>(ctx, new DefaultedEntityGeoModel<TestDummyEntity>(
                        new ResourceLocation(AiEncounters.MOD_ID, "test_dummy"))));
    }

    private ClientSetup() {}
}
