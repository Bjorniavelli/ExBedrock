package com.bjorniavelli.exbedrock;

import com.bjorniavelli.exbedrock.listener.BedrockListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.Set;
import java.util.logging.Logger;

@Mod(modid = ExBedrock.MODID, name = ExBedrock.NAME, version = ExBedrock.VERSION)
public class ExBedrock
{
    // these all need to go to a data class.

    public static final String MODID = "exbedrock";
    public static final String NAME = "Ex Bedrock";
    public static final String VERSION = "1.9-0.0.1";

    public static String configDropCategoryPrefix = "exDrop";

    @Mod.Instance
    public static ExBedrock instance;

    public static File configFile;

    public static Block exBlock;

    public static Set<ExDrop> exDrops;

    // Drop Table
    // Can I do this with a key/value pair system of my own??
    public static Item[][] exToolDrops;
    public static Item[][] exShovelDrops;
    public static Item[][] exPickDrops;
    public static Item[][] exAxeDrops;
    public static Item[][] exHoeDrops;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e)
    {
        Configuration config = new Configuration (e.getSuggestedConfigurationFile());
        config.load();

        // Add Error Checking!
        String exBlockString = config.get("General", "ExBlock", "minecraft:bedrock", "Which block gets pulled from?").getString();
        exBlock = Block.getBlockFromName(exBlockString);

        readDrops (config);

        Set<String> categories = config.getCategoryNames();
        for (String s : categories)
        {
            if (s.startsWith(configDropCategoryPrefix))
            {
                ExDrop.exDrops.add(new ExDrop(config.getCategory(s)));
            }
        }

        config.save();
    }

    public void readDrops(Configuration config)
    {
        // Just using this placeholder to avoid extremely long line lengths
        // But could just as easily do exHandDrops[n] = convertStringlistToItemList(config.get...);
        String[] s;

        exToolDrops = new Item[16][];
        s = config.getStringList("Empty", "Tool Drops", new String[]{"minecraft:stick"}, "Empty Hand Drops");
        exToolDrops[0] = convertStringListToItemList(s);
        s = config.getStringList("NonEmpty", "Tool Drops", new String[]{"minecraft:planks", "minecraft:crafting_table"}, "Non Empty Hand, Non Tool Drops");
        exToolDrops[1] = convertStringListToItemList(s);
        s = config.getStringList("Shear", "Tool Drops", new String[]{}, "Shear drops");
        exToolDrops[2] = convertStringListToItemList(s);
        s = config.getStringList("Fishing", "Tool Drops", new String[]{}, "Fishing Rod Drops");
        exToolDrops[3] = convertStringListToItemList(s);
        s = config.getStringList("Bucket", "Tool Drops", new String[]{}, "Bucket drops");
        exToolDrops[4] = convertStringListToItemList(s);
        s = config.getStringList("Blast", "Tool Drops", new String[]{}, "Drops from exposure to a blast");
        exToolDrops[5] = convertStringListToItemList(s);
        s = config.getStringList("Burn", "Tool Drops", new String[]{}, "Drops from burning");
        exToolDrops[6] = convertStringListToItemList(s);

        exShovelDrops = new Item[16][];
        s = config.getStringList("0", "Shovel Drops", new String[]{}, "Wooden Shovel Drops");
        exShovelDrops[0] = convertStringListToItemList(s);
        s = config.getStringList("1", "Shovel Drops", new String[]{}, "Stone Shovel Drops");
        exShovelDrops[1] = convertStringListToItemList(s);
        s = config.getStringList("2", "Shovel Drops", new String[]{}, "Iron Shovel Drops");
        exShovelDrops[2] = convertStringListToItemList(s);
        s = config.getStringList("3", "Shovel Drops", new String[]{}, "Gold(?) Shovel Drops");
        exShovelDrops[3] = convertStringListToItemList(s);
        s = config.getStringList("4", "Shovel Drops", new String[]{}, "Diamond Shovel Drops");
        exShovelDrops[4] = convertStringListToItemList(s);
        s = config.getStringList("5", "Shovel Drops", new String[]{}, "Modded Level 5 Shovel Drops");
        exShovelDrops[5] = convertStringListToItemList(s);
        s = config.getStringList("6", "Shovel Drops", new String[]{}, "Modded Level 6 Shovel Drops");
        exShovelDrops[6] = convertStringListToItemList(s);
        s = config.getStringList("7", "Shovel Drops", new String[]{}, "Modded Level 7 Shovel Drops");
        exShovelDrops[7] = convertStringListToItemList(s);
        s = config.getStringList("8", "Shovel Drops", new String[]{}, "Modded Level 8 Shovel Drops");
        exShovelDrops[8] = convertStringListToItemList(s);
        s = config.getStringList("9", "Shovel Drops", new String[]{}, "Modded Level 9 Shovel Drops");
        exShovelDrops[9] = convertStringListToItemList(s);
        s = config.getStringList("10", "Shovel Drops", new String[]{}, "Modded Level 10 Shovel Drops");
        exShovelDrops[10] = convertStringListToItemList(s);
        s = config.getStringList("11", "Shovel Drops", new String[]{}, "Modded Level 11 Shovel Drops");
        exShovelDrops[11] = convertStringListToItemList(s);
        s = config.getStringList("12", "Shovel Drops", new String[]{}, "Modded Level 12 Shovel Drops");
        exShovelDrops[12] = convertStringListToItemList(s);
        s = config.getStringList("13", "Shovel Drops", new String[]{}, "Modded Level 13 Shovel Drops");
        exShovelDrops[13] = convertStringListToItemList(s);
        s = config.getStringList("14", "Shovel Drops", new String[]{}, "Modded Level 14 Shovel Drops");
        exShovelDrops[14] = convertStringListToItemList(s);
        s = config.getStringList("15", "Shovel Drops", new String[]{}, "Modded Level 15 Shovel Drops");
        exShovelDrops[15] = convertStringListToItemList(s);

        exPickDrops = new Item[16][];
        s = config.getStringList("0", "Pick Drops", new String[]{"minecraft:cobblestone"}, "Wooden Pick Drops");
        exPickDrops[0] = convertStringListToItemList(s);
        s = config.getStringList("1", "Pick Drops", new String[]{"minecraft:iron_ore"}, "Stone Pick Drops");
        exPickDrops[1] = convertStringListToItemList(s);
        s = config.getStringList("2", "Pick Drops", new String[]{"minecraft:diamond_ore", "minecraft:gold_ore"}, "Iron Pick Drops");
        exPickDrops[2] = convertStringListToItemList(s);
        s = config.getStringList("3", "Pick Drops", new String[]{"minecraft:obsidian"}, "Gold(?) Pick Drops");
        exPickDrops[3] = convertStringListToItemList(s);
        s = config.getStringList("4", "Pick Drops", new String[]{}, "Diamond Pick Drops");
        exPickDrops[4] = convertStringListToItemList(s);
        s = config.getStringList("5", "Pick Drops", new String[]{}, "Modded Level 5 Pick Drops");
        exPickDrops[5] = convertStringListToItemList(s);
        s = config.getStringList("6", "Pick Drops", new String[]{}, "Modded Level 6 Pick Drops");
        exPickDrops[6] = convertStringListToItemList(s);
        s = config.getStringList("7", "Pick Drops", new String[]{}, "Modded Level 7 Pick Drops");
        exPickDrops[7] = convertStringListToItemList(s);
        s = config.getStringList("8", "Pick Drops", new String[]{}, "Modded Level 8 Pick Drops");
        exPickDrops[8] = convertStringListToItemList(s);
        s = config.getStringList("9", "Pick Drops", new String[]{}, "Modded Level 9 Pick Drops");
        exPickDrops[9] = convertStringListToItemList(s);
        s = config.getStringList("10", "Pick Drops", new String[]{}, "Modded Level 10 Pick Drops");
        exPickDrops[10] = convertStringListToItemList(s);
        s = config.getStringList("11", "Pick Drops", new String[]{}, "Modded Level 11 Pick Drops");
        exPickDrops[11] = convertStringListToItemList(s);
        s = config.getStringList("12", "Pick Drops", new String[]{}, "Modded Level 12 Pick Drops");
        exPickDrops[12] = convertStringListToItemList(s);
        s = config.getStringList("13", "Pick Drops", new String[]{}, "Modded Level 13 Pick Drops");
        exPickDrops[13] = convertStringListToItemList(s);
        s = config.getStringList("14", "Pick Drops", new String[]{}, "Modded Level 14 Pick Drops");
        exPickDrops[14] = convertStringListToItemList(s);
        s = config.getStringList("15", "Pick Drops", new String[]{}, "Modded Level 15 Pick Drops");
        exPickDrops[15] = convertStringListToItemList(s);

        exAxeDrops = new Item[16][];
        s = config.getStringList("0", "Axe Drops", new String[]{}, "Wooden Axe Drops");
        exAxeDrops[0] = convertStringListToItemList(s);
        s = config.getStringList("1", "Axe Drops", new String[]{}, "Stone Axe Drops");
        exAxeDrops[1] = convertStringListToItemList(s);
        s = config.getStringList("2", "Axe Drops", new String[]{}, "Iron Axe Drops");
        exAxeDrops[2] = convertStringListToItemList(s);
        s = config.getStringList("3", "Axe Drops", new String[]{}, "Gold(?) Axe Drops");
        exAxeDrops[3] = convertStringListToItemList(s);
        s = config.getStringList("4", "Axe Drops", new String[]{}, "Diamond Axe Drops");
        exAxeDrops[4] = convertStringListToItemList(s);
        s = config.getStringList("5", "Axe Drops", new String[]{}, "Modded Level 5 Axe Drops");
        exAxeDrops[5] = convertStringListToItemList(s);
        s = config.getStringList("6", "Axe Drops", new String[]{}, "Modded Level 6 Axe Drops");
        exAxeDrops[6] = convertStringListToItemList(s);
        s = config.getStringList("7", "Axe Drops", new String[]{}, "Modded Level 7 Axe Drops");
        exAxeDrops[7] = convertStringListToItemList(s);
        s = config.getStringList("8", "Axe Drops", new String[]{}, "Modded Level 8 Axe Drops");
        exAxeDrops[8] = convertStringListToItemList(s);
        s = config.getStringList("9", "Axe Drops", new String[]{}, "Modded Level 9 Axe Drops");
        exAxeDrops[9] = convertStringListToItemList(s);
        s = config.getStringList("10", "Axe Drops", new String[]{}, "Modded Level 10 Axe Drops");
        exAxeDrops[10] = convertStringListToItemList(s);
        s = config.getStringList("11", "Axe Drops", new String[]{}, "Modded Level 11 Axe Drops");
        exAxeDrops[11] = convertStringListToItemList(s);
        s = config.getStringList("12", "Axe Drops", new String[]{}, "Modded Level 12 Axe Drops");
        exAxeDrops[12] = convertStringListToItemList(s);
        s = config.getStringList("13", "Axe Drops", new String[]{}, "Modded Level 13 Axe Drops");
        exAxeDrops[13] = convertStringListToItemList(s);
        s = config.getStringList("14", "Axe Drops", new String[]{}, "Modded Level 14 Axe Drops");
        exAxeDrops[14] = convertStringListToItemList(s);
        s = config.getStringList("15", "Axe Drops", new String[]{}, "Modded Level 15 Axe Drops");
        exAxeDrops[15] = convertStringListToItemList(s);

        exHoeDrops = new Item[16][];
        s = config.getStringList("0", "Hoe Drops", new String[]{}, "Wooden Hoe Drops");
        exHoeDrops[0] = convertStringListToItemList(s);
        s = config.getStringList("1", "Hoe Drops", new String[]{}, "Stone Hoe Drops");
        exHoeDrops[1] = convertStringListToItemList(s);
        s = config.getStringList("2", "Hoe Drops", new String[]{}, "Iron Hoe Drops");
        exHoeDrops[2] = convertStringListToItemList(s);
        s = config.getStringList("3", "Hoe Drops", new String[]{}, "Gold(?) Hoe Drops");
        exHoeDrops[3] = convertStringListToItemList(s);
        s = config.getStringList("4", "Hoe Drops", new String[]{}, "Diamond Hoe Drops");
        exHoeDrops[4] = convertStringListToItemList(s);
        s = config.getStringList("5", "Hoe Drops", new String[]{}, "Modded Level 5 Hoe Drops");
        exHoeDrops[5] = convertStringListToItemList(s);
        s = config.getStringList("6", "Hoe Drops", new String[]{}, "Modded Level 6 Hoe Drops");
        exHoeDrops[6] = convertStringListToItemList(s);
        s = config.getStringList("7", "Hoe Drops", new String[]{}, "Modded Level 7 Hoe Drops");
        exHoeDrops[7] = convertStringListToItemList(s);
        s = config.getStringList("8", "Hoe Drops", new String[]{}, "Modded Level 8 Hoe Drops");
        exHoeDrops[8] = convertStringListToItemList(s);
        s = config.getStringList("9", "Hoe Drops", new String[]{}, "Modded Level 9 Hoe Drops");
        exHoeDrops[9] = convertStringListToItemList(s);
        s = config.getStringList("10", "Hoe Drops", new String[]{}, "Modded Level 10 Hoe Drops");
        exHoeDrops[10] = convertStringListToItemList(s);
        s = config.getStringList("11", "Hoe Drops", new String[]{}, "Modded Level 11 Hoe Drops");
        exHoeDrops[11] = convertStringListToItemList(s);
        s = config.getStringList("12", "Hoe Drops", new String[]{}, "Modded Level 12 Hoe Drops");
        exHoeDrops[12] = convertStringListToItemList(s);
        s = config.getStringList("13", "Hoe Drops", new String[]{}, "Modded Level 13 Hoe Drops");
        exHoeDrops[13] = convertStringListToItemList(s);
        s = config.getStringList("14", "Hoe Drops", new String[]{}, "Modded Level 14 Hoe Drops");
        exHoeDrops[14] = convertStringListToItemList(s);
        s = config.getStringList("15", "Hoe Drops", new String[]{}, "Modded Level 15 Hoe Drops");
        exHoeDrops[15] = convertStringListToItemList(s);
    }

    // Converts a list of strings to their appropriate items from the registry
    // Accepts non-existent items, returns a list of items with no nulls
    public Item[] convertStringListToItemList (String[] sArray)
    {
        Item[] iArray = new Item[sArray.length];
        for (int i = 0; i < sArray.length; i++)
        {
            ResourceLocation r = new ResourceLocation(sArray[i]);
            iArray[i] = Item.itemRegistry.getObject(r);
        }

        return cleanItemArray(iArray);
    }

    // Cleans the null elements from arrays.
    // Primarily arrays read from configs.
    public Item[] cleanItemArray(Item[] iArray)
    {
        int nulls = 0;
        for (int i = 0; i < iArray.length; i++)
        {
            if (iArray[i] == null)
                nulls++;
        }

        if (nulls == 0)
            return iArray;

        Item[] ret = new Item[iArray.length - nulls];
        int j = 0;
        for (int i = 0; i < iArray.length; i++)
        {
            if (iArray[i] != null)
            {
                ret[j] = iArray[i];
                j++;
            }
        }
        return ret;
    }

    @Mod.EventHandler
    public void serverStarting (FMLServerStartingEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new BedrockListener());
    }
}
