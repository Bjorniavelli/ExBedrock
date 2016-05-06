package com.bjorniavelli.exbedrock;

import com.bjorniavelli.exbedrock.listener.BedrockListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.logging.Logger;

@Mod(modid = ExBedrock.MODID, name = ExBedrock.NAME, version = ExBedrock.VERSION)
public class ExBedrock
{
    public static final String MODID = "exbedrock";
    public static final String NAME = "Ex Bedrock";
    public static final String VERSION = "1.9-0.0.1";

    @Mod.Instance
    public static ExBedrock instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e)
    {
    }

    @Mod.EventHandler
    public void serverStarting (FMLServerStartingEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new BedrockListener());
    }
}
