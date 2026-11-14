package com.aiencounters;

import com.aiencounters.registry.ModEntities;
import com.aiencounters.registry.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod(AiEncounters.MOD_ID)
public class AiEncounters {
    public static final String MOD_ID = "ai_encounters";

    public AiEncounters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITIES.register(bus);
        bus.addListener(ModEntities::onAttributes);
        bus.addListener(ModItems::onCreativeTab);
    }
}
