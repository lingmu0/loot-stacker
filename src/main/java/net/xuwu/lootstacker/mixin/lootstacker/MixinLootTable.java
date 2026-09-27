package net.xuwu.lootstacker.mixin.lootstacker;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootTable.class)
public abstract class MixinLootTable {
    @Inject(method = "shuffleAndSplitItems", at = @At("TAIL"))
    private void lootstacker$mergeStackableLoot(
            ObjectArrayList<ItemStack> stacks,
            int emptySlotsCount,
            RandomSource random,
            CallbackInfo callbackInfo
    ) {
        lootstacker$mergeStackableItems(stacks);
    }

    @Unique
    private static void lootstacker$mergeStackableItems(ObjectArrayList<ItemStack> stacks) {
        for (int targetIndex = 0; targetIndex < stacks.size(); targetIndex++) {
            ItemStack target = stacks.get(targetIndex);
            if (target.isEmpty() || target.getMaxStackSize() <= 1) {
                continue;
            }

            for (int sourceIndex = targetIndex + 1;
                 sourceIndex < stacks.size() && target.getCount() < target.getMaxStackSize();
                 sourceIndex++) {
                ItemStack source = stacks.get(sourceIndex);
                if (source.isEmpty()
                        || source.getMaxStackSize() <= 1
                        || !ItemStack.isSameItemSameComponents(target, source)) {
                    continue;
                }

                int transferable = Math.min(
                        target.getMaxStackSize() - target.getCount(),
                        source.getCount()
                );
                target.grow(transferable);
                source.shrink(transferable);
            }
        }

        stacks.removeIf(ItemStack::isEmpty);
    }
}
