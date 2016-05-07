package com.bjorniavelli.exbedrock.listener;

import com.bjorniavelli.exbedrock.ExBedrock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.Random;

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
        World world = player.getEntityWorld();

        ItemStack iStack = getDropFromTool(tool);
//        if (tool == null)
//        {
//            ItemStack iStack = new ItemStack(Items.stick, 1);
//        }

        if (iStack != null)
            world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, iStack));
    }

    public ItemStack getDropFromTool(ItemStack tool)
    {
        if (tool == null)
        {
            return RandomItemStack (ExBedrock.exHandDrops[0]);
        }

        // I need to come up with how to tell what kind of tool we're using.
        if (tool != null)
        {
            return RandomItemStack(ExBedrock.exHandDrops[1]);
//            return new ItemStack (ExBedrock.exHandDrops[1][Math.rint(ExBedrock.exHandDrops[1].length)])
        }

        // This should never happen.  We have a conceptual problem.  Log it?
        return null;
    }

    // Takes an array of Item and returns a random ItemStack from it.
    public ItemStack RandomItemStack(Item[] iArray)
    {
        // does this go here or someplace more central?
        Random r = new Random();

        int index = r.nextInt(iArray.length);
        Item i = iArray[index];

        return new ItemStack(i, 1);
    }
}
