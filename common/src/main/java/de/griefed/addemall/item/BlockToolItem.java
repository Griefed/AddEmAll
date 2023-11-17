package de.griefed.addemall.item;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import de.griefed.addemall.CommonClass;
import de.griefed.addemall.event.KeyInputHandler;
import de.griefed.addemall.platform.Services;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.minecraft.world.item.HoeItem.changeIntoState;
import static net.minecraft.world.item.HoeItem.changeIntoStateAndDropItem;

public class BlockToolItem extends DiggerItem {
//TODO tooltip
    public BlockToolItem() {
        super(12f, 12f, Tiers.NETHERITE, BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties().stacksTo(1).defaultDurability(0).durability(0).fireResistant().tab(Services.PLATFORM.getCreativeTab()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        /*if (hand != InteractionHand.MAIN_HAND) {
            return super.use(level, player, hand);
        }
        if (level.isClientSide()) {
            return super.use(level, player, hand);
        }*/
        //TODO if air, open block selector
        return super.use(level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        ItemStack itemInHand = context.getItemInHand();
        if (player != null && KeyInputHandler.BLOCKTOOL_MULTI_ACTIVE) {
            // Axe
            Optional<BlockState> strippedState = getStripped(blockState);
            Optional<BlockState> previousState = WeatheringCopper.getPrevious(blockState);
            Optional<BlockState> waxedState = Optional.ofNullable(HoneycombItem.WAX_OFF_BY_BLOCK.get().get(block)).map($$1x -> $$1x.withPropertiesOf(blockState));
            Optional<BlockState> optionalBlockState = Optional.empty();
            if (strippedState.isPresent()) {
                level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                optionalBlockState = strippedState;
            } else if (previousState.isPresent()) {
                level.playSound(player, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, blockPos, 0);
                optionalBlockState = previousState;
            } else if (waxedState.isPresent()) {
                level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, blockPos, 0);
                optionalBlockState = waxedState;
            }
            if (optionalBlockState.isPresent()) {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, itemInHand);
                }
                level.setBlock(blockPos, optionalBlockState.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, optionalBlockState.get()));

                return InteractionResult.sidedSuccess(level.isClientSide);

            } else if (block instanceof GrowingPlantHeadBlock headBlock && !headBlock.isMaxAge(blockState)) {
                // shears
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, itemInHand);
                }
                level.playSound(player, blockPos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(blockPos, headBlock.getMaxAgeState(blockState));

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (KeyInputHandler.BLOCKTOOL_SHOVELMODE) {
                // shovel
                if (context.getClickedFace() == Direction.DOWN) {
                    return InteractionResult.PASS;
                } else {
                    BlockState flattenState = FLATTENABLES.get(block);
                    BlockState updatedState = null;
                    if (flattenState != null && level.getBlockState(blockPos.above()).isAir()) {
                        level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                        updatedState = flattenState;
                    } else if (block instanceof CampfireBlock && blockState.getValue(CampfireBlock.LIT)) {
                        if (!level.isClientSide()) {
                            level.levelEvent(null, 1009, blockPos, 0);
                        }

                        CampfireBlock.dowse(player, level, blockPos, blockState);
                        updatedState = blockState.setValue(CampfireBlock.LIT, false);
                    }

                    if (updatedState != null) {
                        if (!level.isClientSide) {
                            level.setBlock(blockPos, updatedState, 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, updatedState));
                        }

                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            } else {
                // hoe
                Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> consumerPair = TILLABLES.get(block);
                if (consumerPair == null) {
                    return InteractionResult.PASS;
                } else {
                    Predicate<UseOnContext> predicate = consumerPair.getFirst();
                    Consumer<UseOnContext> consumer = consumerPair.getSecond();
                    if (predicate.test(context)) {
                        level.playSound(player, blockPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!level.isClientSide) {
                            consumer.accept(context);
                        }

                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }
            return InteractionResult.PASS;
        } else {
            // TODO place selected block
            // TODO decrement selected block material in tool inv
            ((BlockItem) CommonClass.BLOCKS.getEntries().stream().toList().get(0).get().asItem()).place(new BlockPlaceContext(context));
        }
        return super.useOn(context);
    }


    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide()) {
            // TODO increment material in tool inv
            entity.sendSystemMessage(state.getBlock().getName());
        }
        //prevent durability decrease
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return this.speed;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        return true;
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        // indestructible
        return false;
    }

    private Optional<BlockState> getStripped(BlockState blockState) {
        return Optional.ofNullable(STRIPPABLES.get(blockState.getBlock()))
                .map(block -> block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS)));
    }

    public static final Map<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES = new ImmutableMap.Builder<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>>()
            .put(Blocks.GRASS_BLOCK, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState())))
            .put(Blocks.DIRT_PATH, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState())))
            .put(Blocks.DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState())))
            .put(Blocks.COARSE_DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.DIRT.defaultBlockState())))
            .put(Blocks.ROOTED_DIRT, Pair.of($$0 -> true, changeIntoStateAndDropItem(Blocks.DIRT.defaultBlockState(), Items.HANGING_ROOTS)))
            .build();

    public static final Map<Block, BlockState> FLATTENABLES = new ImmutableMap.Builder<Block, BlockState>()
            .put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState())
            .put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState())
            .build();

    public static final Map<Block, Block> STRIPPABLES = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
            .put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
            .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
            .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
            .put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
            .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
            .put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
            .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
            .put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
            .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
            .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
            .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
            .put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
            .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
            .put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
            .put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
            .put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD)
            .put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG)
            .build();
}
