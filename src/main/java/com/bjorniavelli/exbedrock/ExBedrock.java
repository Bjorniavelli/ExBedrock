package com.bjorniavelli.exbedrock;

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

@Mod(modid = ExBedrock.MODID, name = ExBedrock.NAME, version = ExBedrock.VERSION)
public class ExBedrock
{
    // these all need to go to a data class.

    public static final String MODID = "exbedrock";
    public static final String NAME = "Ex Bedrock";
    public static final String VERSION = "1.9-0.0.1";

    public static String configDropCategoryPrefix = "exDrop";

    @Mod.Instance(MODID)
    public static ExBedrock instance;

    public static String DEFAULT_EX_BLOCK = "minecraft:bedrock";

    public static Block exBlock;

    // Drop Table
    public static HashMap<String, ExDrop[]> exDrops = new HashMap<String, ExDrop[]>();

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e)
    {
        File f = e.getSuggestedConfigurationFile();
        Configuration config = new Configuration (e.getSuggestedConfigurationFile());
        config.load();

        String exBlockString = config.get("General", "ExBlock", DEFAULT_EX_BLOCK, "Which block gets pulled from?").getString();
        exBlock = Block.getBlockFromName(exBlockString);
        if (exBlock == null)
        {
            FMLLog.warning("Ex Bedrock: Cannot find block " + exBlockString + ".  Error in config file.  Using " + DEFAULT_EX_BLOCK + ".");
            exBlock = Block.getBlockFromName(DEFAULT_EX_BLOCK);
        }



        // We're still going to try for dynamic...
        // We're just going to require tool, pickaxe, axe, and shovel.  Otherwise we can't apply defaults easily.
        // 'Tool' is required, and omitted.  It's how we start off, but the modpack maker can make it empty.
        // But for example, they might want railcraft stuff to come from crowbars, or aspects from Thaumcraft wands.
        String[] exDropToolTypes = config.getStringList ("General", "ToolTypes", new String[]{}, "Aside from Unnamed, Axe, Pickaxe, and Shovel.  Types of tools to check for valid drop lists for.  Each needs its own category.");

        ExDrop[] exUnnamedDrops = new ExDrop[ExDrop.MAX_HARDNESS_LEVEL];

        // All of these new String[]{}'s need to go into variables in a data class.
        // Unnamed and the other cat names should go to a data variable?
        // Maybe that "0" should just be the name of the hand for these generic tools?
        exUnnamedDrops[0] = new ExDrop(0, "Unnamed", config.getStringList("0", "Unnamed", new String[]{"minecraft:stick"}, "Empty hand"));
        exUnnamedDrops[1] = new ExDrop(1, "Unnamed", config.getStringList("1", "Unnamed", new String[]{"minecraft:planks", "minecraft:crafting_table"}, "Non-tool, unempty hand"));

        exDrops.put("Unnamed", exUnnamedDrops);

        for (String toolName : exDropToolTypes)
        {
            ConfigCategory toolCat = config.getCategory(configDropCategoryPrefix + toolName);

            // This isn't quite right.  We need to skip the standard classes.
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
        return exDrops.get(toolType)[hLevel];
    }
}
