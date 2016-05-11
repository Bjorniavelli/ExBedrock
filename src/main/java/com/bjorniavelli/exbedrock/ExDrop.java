package com.bjorniavelli.exbedrock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;

// Contains all of the info about a particular kind of drop.
public class ExDrop
{
//    public static ArrayList<ExDrop> exDrops = new ArrayList<ExDrop>();
    public static int MAX_HARDNESS_LEVEL = 16;

    public String name;
    public Block hardness; // the block that represents its hardness level.
    public int hardnessLevel; // An ordinal representation of that hardness level.
    public String toolType;
    public ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
    public boolean automatable; // Can this be used by machines?
    public boolean autonomous; // Does this happen on it's own?

    public ExDrop(int hLevel, String type, String[] exDrops)
    {
        // Should we Log out what we're doing here?
        // like, every entry gets it added in a list to the log?

        if (hLevel < 0 || hLevel >= MAX_HARDNESS_LEVEL)
        {
            FMLLog.warning("Ex Bedrock: Invalid hardness level " + hardnessLevel + " Problem in config, category " + name + ".  Setting to " + (MAX_HARDNESS_LEVEL + 1) +".");
            hardnessLevel = MAX_HARDNESS_LEVEL + 1;
        }
        hardnessLevel = hLevel;

        // No error checking.  The toolList is private.
        toolType = type;

        for (String s : exDrops)
        {
            // Just like /give
            // [0]: Minecraft ID
            // [1]: number // Should honestly just be 1, but how about between 1 and this number?  Eventually?
            // [2]: Metadata/blockstate
            // [3]: Any NBT data (Unimplemented)
            // [4+]: ignore?
            String[] sections = s.split(" ");
            FMLLog.info("Ex Bedrock, Processing: " + s);

            if (sections.length == 0)
                continue;

            Item tempItem = Item.getByNameOrId(sections[0]);

            if (tempItem == null)
            {
                FMLLog.warning("Ex Bedrock: Cannot find item " + s + ".  Problem in config, category " + name + ".  Skipping.");
                continue;
            }

            int num = sections.length > 1 ? Integer.parseInt(sections[1]) : 1;

            int metadata = sections.length > 2 ? Integer.parseInt(sections[2]) : 0;
//          int metadata = 0;
//          if (sections.length > 1)
//              metadata = Integer.parseInt(sections[1]);

            NBTTagCompound nbt = null;
            if (sections.length > 3)
            {
                // Nothing, yet.
            }

//            if (metadata != 0 || nbt != null)
//            {
//                // I think we can just add the NBT data here, too.
//                // We might have to store ItemStacks, I'm not sure we can give nbt otherwise.
//                // Samething with metadata?
//                tempItem = new ItemStack(tempItem, 1, metadata, nbt).getItem();
//            }

//            drops.add(tempItem);
            drops.add(new ItemStack(tempItem, num, metadata, nbt));
        }
    }
}
