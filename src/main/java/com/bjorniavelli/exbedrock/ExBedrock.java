package com.bjorniavelli.exbedrock;

import com.bjorniavelli.exbedrock.data.ExData;
import com.bjorniavelli.exbedrock.listener.BedrockListener;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.util.HashMap;

@Mod(modid = ExData.MODID, name = ExData.NAME, version = ExData.VERSION)
public class ExBedrock
{
    // these all need to go to a data class.

    public static String configDropCategoryPrefix = "exDrop";

    @Mod.Instance(ExData.MODID)
    public static ExBedrock instance;

    public static Block exBlock;

    // Drop Table
    public static HashMap<String, ExDrop[]> exDrops = new HashMap<String, ExDrop[]>();

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e)
    {
        File f = e.getSuggestedConfigurationFile();
        Configuration config = new Configuration (e.getSuggestedConfigurationFile());
        config.load();

        String exBlockString = config.get("general", "ExBlock", ExData.DEFAULT_EX_BLOCK, "Which block gets pulled from?").getString();
        exBlock = Block.getBlockFromName(exBlockString);
        if (exBlock == null)
        {
            FMLLog.warning("Ex Bedrock: Cannot find block " + exBlockString + ".  Error in config file.  Using " + ExData.DEFAULT_EX_BLOCK + ".");
            exBlock = Block.getBlockFromName(ExData.DEFAULT_EX_BLOCK);
        }

        // We're still going to try for dynamic...
        // We're just going to require tool, pickaxe, axe, and shovel.  Otherwise we can't apply defaults easily.
        // 'Tool' is required, and omitted.  It's how we start off, but the modpack maker can make it empty.
        // But for example, they might want railcraft stuff to come from crowbars, or aspects from Thaumcraft wands.
        String[] exDropToolTypes = config.getStringList ("ToolTypes", "general", new String[]{}, "Aside from Unnamed, Axe, Pickaxe, and Shovel.  Types of tools to check for valid drop lists for.  Each needs its own category.");

        ExDrop[] exUnnamedDrops = new ExDrop[ExDrop.MAX_HARDNESS_LEVEL];

        // I'd like to add something like:
        // string randomType = config.getString(ExData.RANDOM_TYPE_CONFIG_NAME, ExData.UNNAMED_TOOL, "Uniform", "What kind of distribution of drops", limiters to values?);
        // And then the new ExDrops include that in their constructors
        // And then in ExDrop, we tell it to Random.nextInt(array.size * 10000), and peg it appropriately
        // We could do uniform, standard dist(?), log2, log10, flat
        // uniform would be 1-1-1-1-1, standard would be ~ 1-3-5-3-1, log2 would be ~ 16-8-4-2-1, log10 would be ~ 10000-1000-100-10-1, flat would be 5-4-3-2-1, etc.

        // All of these new String[]{}'s need to go into variables in a data class.
        // Unnamed and the other cat names should go to a data variable?
        // Maybe that "0" should just be the name of the hand for these generic tools?
        exUnnamedDrops[0] = new ExDrop(ExData.EMPTY_HAND, ExData.UNNAMED_TOOL, config.getStringList("0", ExData.UNNAMED_TOOL, ExData.EMPTY_HAND_DEFAULT_DROPS, "Empty hand"));
        exUnnamedDrops[1] = new ExDrop(ExData.NON_TOOL, ExData.UNNAMED_TOOL, config.getStringList("1", ExData.UNNAMED_TOOL, ExData.EMPTY_HAND_NON_TOOL_DROPS, "Non-tool, unempty hand"));

        exDrops.put(ExData.UNNAMED_TOOL, exUnnamedDrops);

        for (String toolName : exDropToolTypes)
        {
            ConfigCategory toolCat = config.getCategory(configDropCategoryPrefix + toolName);

            // This isn't quite right.  We need to skip the standard classes.
            // oh!  Because we have a list of the non-default tools!
        }

        config.save();
    }

    @Mod.EventHandler
    public void serverStarting (FMLServerStartingEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new BedrockListener());
    }

    public static ExDrop getExDrops(String toolType, int hLevel)
    {
        ExDrop[] temp = exDrops.get(toolType);

        if (temp != null && temp.length >= hLevel)
            return temp[hLevel];
        else
            return null;
    }
}
