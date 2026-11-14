package com.aiencounters.registry;

import com.aiencounters.AiEncounters;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AiEncounters.MOD_ID);

    /**
     * Design v0.3 §04: damage 6, attack speed 1.6. A SwordItem's displayed damage is
     * tier bonus + attackDamageModifier + 1 (base hand), so 6 total => modifier 3 with the
     * 2.0-bonus iron tier. Speed modifier is relative to the 4.0 base: 1.6 - 4.0 = -2.4.
     */
    public static final RegistryObject<Item> TRAVELER_SWORD = ITEMS.register("traveler_sword",
            () -> new SwordItem(Tiers.IRON, 3, -2.4f, new Item.Properties()));

    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(TRAVELER_SWORD);
        }
    }

    private ModItems() {}
}
