package com.bjorniavelli.exbedrock;

import com.bjorniavelli.exbedrock.listener.BedrockListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
import java.util.logging.Logger;

@Mod(modid = ExBedrock.MODID, name = ExBedrock.NAME, version = ExBedrock.VERSION)
public class ExBedrock
{
    public static final String MODID = "exbedrock";
    public static final String NAME = "Ex Bedrock";
    public static final String VERSION = "1.9-0.0.1";

    @Mod.Instance
    public static ExBedrock instance;

    public static File configFile;

    public static Block exBlock;

    // Drop Table
    // Can I do this with a key/value pair system of my own??
    public static Item[][] exHandDrops;
    public static Item[][] exShovelDrops;
    public static Item[][] exPickDrops;
    public static Item[][] exAxeDrops;
    public static Item[][] exHoeDrops;
    public static Item[][] exShearDrops;
    public static Item[][] exFishingDrops;
    public static Item[][] exBucketDrops;
    public static Item[][] exBlastDrops;
    public static Item[][] exBurnDrops;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e)
    {
        Configuration config = new Configuration (e.getSuggestedConfigurationFile());
        config.load();

        // Add Error Checking!
        String exBlockString = config.get("General", "ExBlock", "minecraft:bedrock", "Which block gets pulled from?").getString();
        exBlock = Block.getBlockFromName(exBlockString);

        // Just using this placeholder to avoid extremely long line lengths
        // But could just as easily do exHandDrops[n] = convertStringlistToItemList(config.get...);
        String[] s;

        exHandDrops = new Item[2][];
        s = config.getStringList("0", "Hand Drops", new String[]{"minecraft:stick"}, "Empty Hand Drops");
        exHandDrops[0] = convertStringListToItemList(s);
        s = config.getStringList("1", "Hand Drops", new String[]{"minecraft:planks"}, "Non Empty Hand, Non Tool Drops");
        exHandDrops[1] = convertStringListToItemList(s);

        config.save();
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
