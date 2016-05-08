package com.bjorniavelli.exbedrock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Contains all of the info about a particular kind of drop.
public class ExDrop
{
    // Static refernce containing a set of all ExDrops.
    // Errmmm... do I want to do it like this?  Seems wrong, but I really only want a Set.
    public static Set<ExDrop> exDrops = new HashSet<ExDrop>();
    public static int MAX_HARDNESS_LEVEL = 16;


    public String name;
    public Block hardness; // the block that represents its hardness level.
    public int hardnessLevel; // An ordinal representation of that hardness level.
    public Item toolType; // Not sure how this is going to happen.
//    public String type;  // String or Item?
    public ArrayList<Item> drops;
    public boolean automatable; // Can this be used by machines?
    public boolean autonomous; // Does this happen on it's own?

    public ExDrop(ConfigCategory config)
    {
        name = config.getName();

        // Hardness level determines what order we check for drops in.
        // Hardness is arbitrarily limited to 16 levels.  That can be changed.
        hardnessLevel = config.get("hardnessLevel").getInt(-1);
        if (hardnessLevel < 0 || hardnessLevel >= MAX_HARDNESS_LEVEL)
        {
            FMLLog.warning("Ex Bedrock: Invalid hardness level " + hardnessLevel + " Problem in config, category " + name + ".  Setting to " + (MAX_HARDNESS_LEVEL + 1) +".");
            hardnessLevel = MAX_HARDNESS_LEVEL + 1;
        }


        // What hardness this should be able to mine goes here.
        String block = config.get("hardness").getString();
        hardness = Block.getBlockFromName(block);
        if (hardness == null)
        {
            FMLLog.warning("Ex Bedrock: Cannot find block " + hardness + ".  Problem in config, category " + name + ".  Setting to Bedrock.");
            hardness = Blocks.bedrock;
        }

        //
        // Not sure how to do toolType.
        //

        String[] exdrops = config.get("exDrops").getStringList();
        for (String s : exdrops)
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

        // Automatable and requirePlayer are not required
        automatable = config.get("automatable").getBoolean(true);
        autonomous = config.get("autonomous").getBoolean(false);
    }
}
