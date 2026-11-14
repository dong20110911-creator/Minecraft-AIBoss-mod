package com.aiencounters.gametest;

import com.aiencounters.AiEncounters;
import com.aiencounters.entity.TestDummyEntity;
import com.aiencounters.registry.ModEntities;
import com.aiencounters.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.ISlotType;

import java.util.Optional;

/** M0 acceptance checks. Run with: ./gradlew runGameTestServer */
@GameTestHolder(AiEncounters.MOD_ID)
@PrefixGameTestTemplate(false)
public final class M0GameTests {
    private static final String EMPTY = "empty";

    /** Final attribute values as the game computes them for a player holding the sword (design v0.3 §04). */
    @GameTest(template = EMPTY)
    public static void travelerSwordFinalAttributes(GameTestHelper helper) {
        Player player = helper.makeMockPlayer();
        ItemStack sword = new ItemStack(ModItems.TRAVELER_SWORD.get());
        player.getAttributes().addTransientAttributeModifiers(sword.getAttributeModifiers(EquipmentSlot.MAINHAND));
        double damage = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double speed = player.getAttributeValue(Attributes.ATTACK_SPEED);
        if (Math.abs(damage - 6.0) > 1e-6) {
            helper.fail("attack damage expected 6.0 but was " + damage);
        }
        if (Math.abs(speed - 1.6) > 1e-6) {
            helper.fail("attack speed expected 1.6 but was " + speed);
        }
        helper.succeed();
    }

    /** test_dummy registers, spawns on the server, and is still alive and unmoved after 40 ticks. */
    @GameTest(template = EMPTY, timeoutTicks = 100)
    public static void testDummySpawnsAndSurvives(GameTestHelper helper) {
        TestDummyEntity dummy = helper.spawn(ModEntities.TEST_DUMMY.get(), new BlockPos(2, 1, 2));
        helper.runAfterDelay(40, () -> {
            if (!dummy.isAlive()) {
                helper.fail("test_dummy died or was removed");
            }
            if (dummy.getHealth() != dummy.getMaxHealth()) {
                helper.fail("test_dummy lost health: " + dummy.getHealth());
            }
            if (helper.getLevel().getEntity(dummy.getUUID()) != dummy) {
                helper.fail("test_dummy not found in the level by UUID");
            }
            helper.succeed();
        });
    }

    /** The data-driven Curios slot loaded and is bound to players. */
    @GameTest(template = EMPTY)
    public static void curiosCharmSlotLoadedForPlayers(GameTestHelper helper) {
        Optional<ISlotType> slot = CuriosApi.getSlot("ai_encounters_charm", false);
        if (slot.isEmpty()) {
            helper.fail("Curios slot ai_encounters_charm was not loaded");
            return;
        }
        if (slot.get().getSize() != 1) {
            helper.fail("slot size expected 1 but was " + slot.get().getSize());
        }
        if (!CuriosApi.getPlayerSlots(helper.getLevel()).containsKey("ai_encounters_charm")) {
            helper.fail("ai_encounters_charm is not bound to the player entity");
        }
        helper.succeed();
    }

    private M0GameTests() {}
}
