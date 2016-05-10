package com.bjorniavelli.exbedrock.listener;

import com.bjorniavelli.exbedrock.ExBedrock;
import com.bjorniavelli.exbedrock.ExDrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

// I need this to be sideonly, I think.  Because it drops ghosts on the ground,
// But Server makes nothing happen, and Client makes the ghosts still appear.  o_O
public class BedrockListener
{
    // Should listen to all player interactions and drop out if it isn't bedrock.
    @SubscribeEvent
    public void onPlayerInteract (PlayerInteractEvent.LeftClickBlock e)
    {
        BlockPos target = e.getPos();
        IBlockState state = e.getWorld().getBlockState(target);
        Block b = state.getBlock();
        // This should be dropped to a config.
        // Then modpack makers can choose which block gives items
        if (b != ExBedrock.exBlock)
        {
            return;
        }

        // Needs to drop to a config whhether either or both is ok.
        if (e.getHand() != EnumHand.MAIN_HAND)
        {
            return;
        }

        ItemStack tool = e.getItemStack();
        EntityPlayer player = e.getEntityPlayer();

        if (tool == null)
        {
            spawnAtPlayer(player, RandomItemStack(ExBedrock.getExDrops("Unnamed", 0)));
            return;
        }

        if (!tool.getItem().isItemTool(tool))
        {
            // Include logic to check for common tools, but for now...
            spawnAtPlayer(player, RandomItemStack(ExBedrock.getExDrops("Unnamed", 1)));
        }

//        Item item = tool.getItem();
//        FMLLog.info(tool.getItem() + " <----- tool.getItem()");
//
//        if (item == null) return;
//
//        FMLLog.info(item.isItemTool(tool) + " <----- .isItemTool(tool) ??");
//
//        Set<String> toolclass = item.getToolClasses(tool);
//
//        for (String s : toolclass)
//        {
//            FMLLog.info(s + " <---- .getToolClasses(tool) for loop");
//            FMLLog.info(item.getHarvestLevel(tool, s) + " <---- .getHarvestLevel(tool, s) for loop");
//        }
//
////        ItemStack iStack = getDropFromTool(tool);
//////        if (tool == null)
//////        {
//////            ItemStack iStack = new ItemStack(Items.stick, 1);
//////        }
////
////        if (iStack != null)
////            world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, iStack));
    }

//    public ItemStack getDropFromTool(ItemStack tool)
//    {
//        if (tool == null)
//        {
//            return RandomItemStack (ExBedrock.exToolDrops[0]);
//        }
//
//
//
////        // Doing it this way is the way to go for vanilla modding...
////        // but how to I extend it to play nicely with other mods?
////        // Also, maybe a switch would be better?
////        // Either way, there's lots of repetitive code here...
////
////        if (tool.getItem() == Items.diamond_pickaxe)
//////        if (tool.canHarvestBlock(Blocks.obsidian))
////        {
////            return RandomItemStack(ExBedrock.exPickDrops[3]);
////        }
////
////        if (tool.getItem() == Items.iron_pickaxe)
//////        if (tool.canHarvestBlock(Blocks.diamond_ore))
////        {
////            return RandomItemStack(ExBedrock.exPickDrops[2]);
////        }
////
////        if (tool.getItem() == Items.stone_pickaxe)
//////        if (tool.canHarvestBlock(Blocks.iron_ore))
////        {
////            return RandomItemStack(ExBedrock.exPickDrops[1]);
////        }
////
////        if (tool.getItem() == Items.wooden_pickaxe)
//////        if (tool.canHarvestBlock(Blocks.cobblestone))
////        {
////            return RandomItemStack(ExBedrock.exPickDrops[0]);
////        }
////
////        if (tool.getItem() == Items.wooden_axe)
//////        if (tool.canHarvestBlock(Blocks.log))
////        {
////            return RandomItemStack(ExBedrock.exAxeDrops[0]);
////        }
////
////        // Need to add the other things, but there's gotta be a better way to do this...
////        // Shears, Hoes, Burning, etc...
////
////        // I need to come up with how to tell what kind of tool we're using.
////        if (tool != null)
////        {
////            return RandomItemStack(ExBedrock.exToolDrops[1]);
//////            return new ItemStack (ExBedrock.exHandDrops[1][Math.rint(ExBedrock.exHandDrops[1].length)])
////        }
//
//        // This should never happen.  We have a conceptual problem.  Log it?
//        return null;
//    }

    // Takes an array of Item and returns a random ItemStack from it.
    public ItemStack RandomItemStack(ExDrop e)
    {
        ArrayList<Item> iArray = e.drops;
        // This is so horrible... we should handle this better for now.
//        Item[] iArray = (Item[])e.drops.toArray();

        if (iArray.isEmpty())
            return null;
//        if (iArray.length == 0)
//            return null;

        // does this go here or someplace more central?
        Random r = new Random();

        int index = r.nextInt(iArray.size());
        Item i = iArray.get(index);

        return new ItemStack(i, 1);
    }

    public void spawnAtPlayer (EntityPlayer player, ItemStack iStack)
    {
        if (player == null)
        {
            FMLLog.warning("Ex Bedrock: Tried to spawn an ItemStack at a nonexistent player.  Skipping.");
            return;
        }

        if (iStack == null) {
            FMLLog.warning("Ex Bedrock: Tried to spawn a non-existent ItemStack. Skipping.");
            return;
        }

        World world = player.getEntityWorld();
        world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, iStack));
    }
}
