package com.bjorniavelli.exbedrock;

import com.bjorniavelli.exbedrock.listener.BedrockListener;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e)
    {
        Configuration config = new Configuration (e.getSuggestedConfigurationFile());
        config.load();

        String exBlockString = config.get("General", "ExBlock", "minecraft:bedrock", "Which block gets pulled from?").getString();
        //It works for now if we give it an actual block, but I don't know how to error check that, yet.
//        if (Block.getBlockFromName(exBlockString) != Null)
//        {
//            exBlock = Block.getBlockFromName(exBlockString));
//        }

        exBlock = Block.getBlockFromName(exBlockString);
        FMLLog.info (exBlock.toString() + " <--- exBlock");
        config.save();
    }

    @Mod.EventHandler
    public void serverStarting (FMLServerStartingEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new BedrockListener());
    }
}
