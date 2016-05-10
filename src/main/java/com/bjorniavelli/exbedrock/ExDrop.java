package com.bjorniavelli.exbedrock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;

// Contains all of the info about a particular kind of drop.
public class ExDrop
{
    // Static refernce containing a set of all ExDrops.
    // Errmmm... do I want to do it like this?  Seems wrong, but I really only want a Set.
    public static ArrayList<ExDrop> exDrops = new ArrayList<ExDrop>();
    public static int MAX_HARDNESS_LEVEL = 16;


    public String name;
    public Block hardness; // the block that represents its hardness level.
    public int hardnessLevel; // An ordinal representation of that hardness level.
//    public teIm toolType; // Not sure how this is going to happen.
    public String toolType;  // String or Item?
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

        // I don't think we can get the established list of allowed tools... :-(
        // It's private.
//        if (ItemTool.getToolClasses())
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

//    public ExDrop(ConfigCategory config)
//    {
//        name = config.getName();
//
//        // Hardness level determines what order we check for drops in.
//        // Hardness is arbitrarily limited to 16 levels.  That can be changed.
//        hardnessLevel = config.get("hardnessLevel").getInt(-1);
//        if (hardnessLevel < 0 || hardnessLevel >= MAX_HARDNESS_LEVEL)
//        {
//            FMLLog.warning("Ex Bedrock: Invalid hardness level " + hardnessLevel + " Problem in config, category " + name + ".  Setting to " + (MAX_HARDNESS_LEVEL + 1) +".");
//            hardnessLevel = MAX_HARDNESS_LEVEL + 1;
//        }
//
//
//        // What hardness this should be able to mine goes here.
//        String block = config.get("hardness").getString();
//        hardness = Block.getBlockFromName(block);
//        if (hardness == null)
//        {
//            FMLLog.warning("Ex Bedrock: Cannot find block " + hardness + ".  Problem in config, category " + name + ".  Setting to Bedrock.");
//            hardness = Blocks.bedrock;
//        }
//
//        //
//        // Not sure how to do toolType.
//        //
//
//        String[] exdrops = config.get("exDrops").getStringList();
//        for (String s : exdrops)
//        {
//            Item tempItem = Item.getByNameOrId(s);
//            if (tempItem == null)
//            {
//                FMLLog.warning("Ex Bedrock: Cannot find item " + s + ".  Problem in config, category " + name + ".  Skipping.");
//            }
//            else
//            {
//                drops.add(tempItem);
//            }
//        }
//
//        // Automatable and requirePlayer are not required
//        automatable = config.get("automatable").getBoolean(true);
//        autonomous = config.get("autonomous").getBoolean(false);
//    }
}
