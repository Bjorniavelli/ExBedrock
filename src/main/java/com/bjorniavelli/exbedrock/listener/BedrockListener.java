package com.bjorniavelli.exbedrock.listener;

import com.bjorniavelli.exbedrock.ExBedrock;
import com.bjorniavelli.exbedrock.ExDrop;
import com.bjorniavelli.exbedrock.data.ExData;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Random;

// Cricket says to do this server side and send the player a packet.  ??
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
            spawnAtPlayer(player, RandomItemStack(ExBedrock.getExDrops(ExData.UNNAMED_TOOL, ExData.EMPTY_HAND)));
            return;
        }

        if (!tool.getItem().isItemTool(tool))
        {
            // Include logic to check for common tools, but for now...
            spawnAtPlayer(player, RandomItemStack(ExBedrock.getExDrops(ExData.UNNAMED_TOOL, ExData.NON_TOOL)));
        }
    }


    // Takes an exDrop and returns a random ItemStack from it.
    public ItemStack RandomItemStack(ExDrop e)
    {
        ArrayList<ItemStack> iArray = e.drops;

        if (iArray.isEmpty())
            return null;

        // does this go here or someplace more central?
        Random r = new Random();

        int index = r.nextInt(iArray.size());
//        Item i = iArray.get(index);
        ItemStack ret = iArray.get(index).copy();

        FMLLog.info ("EX BEDROCK: stacksize: " + ret.stackSize);
        if (ret.stackSize > 1)
            ret.stackSize = r.nextInt(ret.stackSize) + 1;

        return ret;
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
