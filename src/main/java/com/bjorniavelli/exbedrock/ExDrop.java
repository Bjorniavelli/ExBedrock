package com.bjorniavelli.exbedrock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;

// Contains all of the info about a particular kind of drop.
public class ExDrop
{
    public static ArrayList<ExDrop> exDrops = new ArrayList<ExDrop>();
    public static int MAX_HARDNESS_LEVEL = 16;


    public String name;
    public Block hardness; // the block that represents its hardness level.
    public int hardnessLevel; // An ordinal representation of that hardness level.
    public String toolType;
    public ArrayList<Item> drops = new ArrayList<Item>();
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
            Item tempItem = Item.getByNameOrId(s);
            if (tempItem == null)
            {
                FMLLog.warning("Ex Bedrock: Cannot find item " + s + ".  Problem in config, category " + name + ".  Skipping.");
            }
            else
            {
                drops.add(tempItem);
            }
        }
    }
}
