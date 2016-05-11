package com.bjorniavelli.exbedrock.data;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class ExData
{
    // Mod Data
    public static final String MODID = "exbedrock";
    public static final String NAME = "Ex Bedrock";
    public static final String VERSION = "1.9-0.0.1";

    // String Defaults
    public static final String DEFAULT_EX_BLOCK = "minecraft:bedrock";
    public static final String UNNAMED_TOOL = "unnamed";
    public static final String PICKAXE_TOOL = "pickaxe";
    public static final String AXE_TOOL = "axe";
    public static final String SHOVEL_TOOL = "shovel";

    // Non-tool ranks
    // Do we want an enum for this?  Seems overly complex.
    public static final int EMPTY_HAND = 0;
    public static final int NON_TOOL = 1;
    public static final int SHEAR = 2;
    public static final int FISHING_ROD = 3;
    public static final int HOE = 4;
    public static final int BUCKET = 5;

    // Environmental ranks
    public static final int BLAST_RANK = 0;
    public static final int BURN_RANK = 1;
    public static final int WATER_RANK = 2;

    // Tool ranks
    public static final int WOOD_RANK = 0;
    public static final int STONE_RANK = 1;
    public static final int IRON_RANK = 2;
    public static final int DIAMOND_RANK = 3;

    // Should we seperate out environmental things?
    // Probably...

    // Config Defaults
    public static final String[] EMPTY_HAND_DEFAULT_DROPS = new String[]
            {
                    "minecraft:stick"
            };
    public static final String[] EMPTY_HAND_NON_TOOL_DROPS = new String[]
            {
                    "minecraft:planks",
                    "minecraft:planks 1 1",
                    "minecraft:planks 1 2",
                    "minecraft:planks 1 3",
                    "minecraft:planks 1 4",
                    "minecraft:planks 1 5"
            };
    public static final String[] SHEAR_DEFAULT_DROPS = new String[]
            {
                    "minecraft:string"
            };
    public static final String[] FISHING_ROD_DEFAULT_DROPS = new String[]
            {
                    "minecraft:waterlily"
            };
    public static final String[] HOE_DEFAULT_DROPS = new String[] // Would like to have tiers... but oh well.
            {
                    "minecraft:wheat_seeds"
            };
    public static final String[] BUCKET_DEFAULT_DROPS = new String[]
            {
                    "minecraft:water_bucket"
            };
    // public static final String[] COMPASS_DEFAULT_DROPS = new String[] {}; // Low priority, drop filled in maps from other worlds.

    // Environmental Drops
    public static final String[] BLAST_DEFAULT_DROPS = new String[]
            {
                    "minecraft:tnt" // Eh... this is just a placeholder
            };
    public static final String[] BURN_DEFAULT_DROPS = new String[]
            {
                    "minecraft:nether_wart"
            };
    public static final String[] WATER_DEFAULT_DROPS = new String[]
            {
                    // Nothing for now...
                    // Eventually, Bucket o' Liquid Bedrock
            };

    // Pickaxe Drops
    public static final String[] PICKAXE_WOOD_DEFAULT_DROPS = new String[]
            {
                    "minecraft:cobblestone"
            };
    public static final String[] PICKAXE_STONE_DEFAULT_DROPS = new String[]
            {
                    "minecraft:iron_ore"
            };
    public static final String[] PICKAXE_IRON_DEFAULT_DROPS = new String[]
            {
                    "minecraft:gold_ore"
            };
    public static final String[] PICKAXE_DIAMOND_DEFAULT_DROPS = new String[]
            {
                    "minecraft:diamond_ore"
            };

    // Axe Drops
    public static final String[] AXE_WOOD_DEFAULT_DROPS = new String[]
            {
                    "minecraft:planks:3",
                    "minecraft:log"
            };
    public static final String[] AXE_STONE_DEFAULT_DROPS = new String[]
            {
                    "minecraft:ice"
            };
    public static final String[] AXE_IRON_DEFAULT_DROPS = new String[]
            {
                    "minecraft:sapling"
            };
    public static final String[] AXE_DIAMOND_DEFAULT_DROPS = new String[]
            {
                    "minecraft:reeds" // eh?
            };

    // Shovel Drops
    public static final String[] SHOVEL_WOOD_DEFAULT_DROPS = new String[]
            {
                    "minecraft:dirt"
            };
    public static final String[] SHOVEL_STONE_DEFAULT_DROPS = new String[]
            {
                    "minecraft:sand",
                    "minecraft:gravel"
            };
    public static final String[] SHOVEL_IRON_DEFAULT_DROPS = new String[]
            {
                    "minecraft:soul_sand"
            };
    public static final String[] SHOVEL_DIAMOND_DEFAULT_DROPS = new String[]
            {
                    "minecraft:grass",
                    "minecraft:mycelium",
                    "minecraft:podzoi:2"
                    // Podzoi?
            };
}