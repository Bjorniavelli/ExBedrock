package com.bjorniavelli.exbedrock.listener;

import com.bjorniavelli.exbedrock.ExBedrock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

        // Create the ItemStack
        // Obviously this will need to pull from a list of possibilities.
        ItemStack iStack = new ItemStack(Item.getByNameOrId("minecraft:stick"), 1);
        // Create the EntityItem from it
        EntityItem eItem = new EntityItem(e.getEntityPlayer().getEntityWorld(), e.getEntityPlayer().posX, e.getEntityPlayer().posY, e.getEntityPlayer().posZ, iStack);
        // Spawn the item where the player is.
        // Lots of different places to spawn it, but where the player is should prevent it from being lost in a wall of bedrock.
        e.getEntityPlayer().getEntityWorld().spawnEntityInWorld(eItem);
    }

}
