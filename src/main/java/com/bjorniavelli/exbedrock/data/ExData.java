package com.bjorniavelli.exbedrock.data;

import com.sun.deploy.config.DefaultConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import scala.tools.nsc.doc.model.Def;

public class ExData
{
    // Mod Data
    public static final String MODID = "exbedrock";
    public static final String NAME = "Ex Bedrock";
    public static final String VERSION = "1.9-0.0.1";

    // String Defaults
    public static final String DEFAULT_EX_BLOCK = "minecraft:bedrock";
    public static final String UNNAMED_TOOL = "unnamed";
    public static final String ENVIRONMENTAL = "environmental";
    public static final String PICKAXE_TOOL = "pickaxe";
    public static final String AXE_TOOL = "axe";
    public static final String SHOVEL_TOOL = "shovel";

    // Tool ranks
    public static final int WOOD_RANK = 0;
    public static final int STONE_RANK = 1;
    public static final int IRON_RANK = 2;
    public static final int DIAMOND_RANK = 3;


    // should these go into DefaultConfigData?
    // Non-Tool Drops
    public static final DefaultConfigData EMPTY_HAND = new DefaultConfigData(
            0,
            "Drops from an empty hand",
            ExData.UNNAMED_TOOL,
            new String[]
                    {
                            "minecraft:stick"
                    }
    );
    public static final DefaultConfigData NON_TOOL = new DefaultConfigData(
            1,
            "Drops from a non_tool item",
            ExData.UNNAMED_TOOL,
            new String[]
                    {
                            "minecraft:planks",
                            "minecraft:coal"
                    }
    );
    public static final DefaultConfigData SHEAR = new DefaultConfigData(
            2,
            "Drops from a shear",
            ExData.UNNAMED_TOOL,
            new String[]
                    {
                            "minecraft:string"
                    }
    );
    public static final DefaultConfigData FISHING_ROD = new DefaultConfigData(
            3,
            "Drops from a fishing rod",
            ExData.UNNAMED_TOOL,
            new String[]
                    {
                            "minecraft:waterlily",
                            "minecraft:slimeball",
                            "minecraft:prismarine_shard"
                    }
    );
    public static final DefaultConfigData HOE = new DefaultConfigData(
            4,
            "Drops from a hoe",
            ExData.UNNAMED_TOOL,
            new String[]
                    {
                            "minecraft:waterlily",
                            "minecraft:slimeball",
                            "minecraft:prismarine_shard"
                    }
    );
    public static final DefaultConfigData BUCKET = new DefaultConfigData(
            5,
            "Drops from a bucket",
            ExData.UNNAMED_TOOL,
            new String[]
                    {
                            "minecraft:water_bucket"
                    }
    );
    // public static final String[] COMPASS_DEFAULT_DROPS = new String[] {}; // Low priority, drop filled in maps from other worlds.
    // public static final String[] SPONGE_DROPS = new String[] {};

    // Environmental Drops
    public static final DefaultConfigData BLAST = new DefaultConfigData(
            0,
            "Drops from being exploded",
            ExData.ENVIRONMENTAL,
            new String[]
                    {
                            "minecraft:quartz",
                            "minecraft:glowstone_dust",
                            "minecraft:end_portal", // Eventually, we need this to be not so easy
                            "minecraft:end_stone" // These need their own mechanic.  // Maybe they onle happen in the Bedrock End?
                            //end rod?
                            // chorus fruit?
                    }
    );
    public static final DefaultConfigData BURN = new DefaultConfigData(
            1,
            "Drops from being on fire",
            ExData.ENVIRONMENTAL,
            new String[]
                    {
                            "minecraft:nether_wart",
                            "minecraft:netherrack" // similarly, then maybe these nly happen in The Bedrock Nether?
                            // Need some way of getting wither skulls
                            // Need some way of getting blaze rods
                            // maybe zombie spawner on top of bedrock makes blazes?
                            // Skeleton spawner makes wither skeletons?
                            // Ghasts should form in a big enough area in the nether?
                    }
    );
    public static final DefaultConfigData WATER = new DefaultConfigData(
            2,
            "Drops from being next to flowing water",
            ExData.ENVIRONMENTAL,
            new String[]
                    {
                            "minecraft:sponge 1 1",
                            "minecraft:reeds",
                            // Eventually, Bucket o' Liquid Bedrock
                    }
    );
    // Or wait!  Could we have sponge get used on the bedrock as a tool,
    // And it gives you bedrock-laden sponge, which you can somehow make into liquid bedrock?

    // What about being near Lava?

    // This is probably the best place to get some of the blocks?
    //public static final String[] GROWTH_DEFAULT_DROPS = new String[] {};
    // I might put carrots and stuff in here instead of from hoeing the bedrock?
    // public static final String[] FARMLAND_DEFAULT_DROPS = new String[] {};
    // I'd like something here along the lines of 'if water is flowing next to bedrock,
    // And it gives stone, there's a chance it'll be mossy stone?
    // What about public static final String[] MOB_SPAWNER_DEFAULT... ?
    // Some of these may just need to be changed to behaviours, not drops...
    // public static final String[] ICE_DEFAULT... // So we can have snow form on the bedrock?
    // Or should that be vice versa somehow?

    //Pickaxe Drops
    public static final DefaultConfigData WOOD_PICKAXE = new DefaultConfigData(
            WOOD_RANK,
            "Drops from a wooden pickaxe",
            ExData.PICKAXE_TOOL,
            new String[]
                    {
                            "minecraft:cobblestone"
                    }
    );
    public static final DefaultConfigData STONE_PICKAXE = new DefaultConfigData(
            STONE_RANK,
            "Drops from a stone pickaxe",
            ExData.PICKAXE_TOOL,
            new String[]
                    {
                            "minecraft:iron_ore",
                            "minecraft:stone 1 1", // Granite
                            "minecraft:stone 1 3", // Diorite
                            "minecraft:stone 1 5", // Andesite
                    }
    );
    public static final DefaultConfigData IRON_PICKAXE = new DefaultConfigData(
            IRON_RANK,
            "Drops from an iron pickaxe",
            ExData.PICKAXE_TOOL,
            new String[]
                    {
                            "minecraft:lapis_ore",
                            "minecraft:diamond_ore"
                    }
    );
    public static final DefaultConfigData DIAMOND_PICKAXE = new DefaultConfigData(
            DIAMOND_RANK,
            "Drops from a diamond pickaxe",
            ExData.PICKAXE_TOOL,
            new String[]
                    {
                            "minecraft:obsidian", // this isn't perfect, but we need a way to get it without lava, yet.
                            "minecraft:gold_ore",
                            "minecraft:redstone_ore",
                            "minecraft:emerald_ore" // This may be unnecessary.  Depends on whether we can get villagers easily.  Could just force people to make them out of zombie villagers, and then trade with them...
                    }
    );

    // Axe Drops
    public static final DefaultConfigData WOOD_AXE = new DefaultConfigData(
            WOOD_RANK,
            "Drops from a wooden axe",
            ExData.AXE_TOOL,
            new String[]
                    {
                            "minecraft:planks 1 1", // Spruce
                            "minecraft:planks 1 2", // Birch
                            "minecraft:planks 1 3", // Jungle
                            "minecraft:planks 1 4", // Acacia
                            "minecraft:planks 1 5" // Dark Oak
                    }
    );
    public static final DefaultConfigData STONE_AXE = new DefaultConfigData(
            STONE_RANK,
            "Drops from a stone axe",
            ExData.AXE_TOOL,
            new String[]
                    {
                            "minecraft:log",
                            "minecraft:ice"
                    }
    );
    public static final DefaultConfigData IRON_AXE = new DefaultConfigData(
            IRON_RANK,
            "Drops from an iron axe",
            ExData.AXE_TOOL,
            new String[]
                    {
                            "minecraft:sapling",
                            "minecraft:log 1 1", // Spruce
                            "minecraft:log 1 2", // Birch
                            "minecraft:log 1 3", // Jungle
                            "minecraft:log 1 4", // Acacia
                            "minecraft:log 1 5", // Dark Oak
                    }
    );
    public static final DefaultConfigData DIAMOND_AXE = new DefaultConfigData(
            DIAMOND_RANK,
            "Drops from a diamond axe",
            ExData.AXE_TOOL,
            new String[]
                    {
                            "minecraft:sapling 1 1", // Spruce
                            "minecraft:sapling 1 2", // Birch
                            "minecraft:sapling 1 3", // Jungle
                            "minecraft:sapling 1 4", // Acacia
                            "minecraft:sapling 1 5", // Dark Oak
                            "minecraft:pumpkin",
                            "minecraft:melon_block"
                    }
    );

    // Shovel Ranks
    public static final DefaultConfigData WOOD_SHOVEL = new DefaultConfigData(
            WOOD_RANK,
            "Drops from a wooden shovel",
            ExData.SHOVEL_TOOL,
            new String[]
                    {
                            "minecraft:dirt"
                    }
    );
    public static final DefaultConfigData STONE_SHOVEL = new DefaultConfigData(
            STONE_RANK,
            "Drops from a stone shovel",
            ExData.SHOVEL_TOOL,
            new String[]
                    {
                            "minecraft:sand",
                            "minecraft:gravel",
                            "minecraft:clay_ball"
                    }
    );
    public static final DefaultConfigData IRON_SHOVEL = new DefaultConfigData(
            IRON_RANK,
            "Drops from an iron shovel",
            ExData.SHOVEL_TOOL,
            new String[]
                    {
                            "minecraft:soul_sand", // Better if we add that it can only happen while it's burning.
                            "minecraft:sand 1 1"
                    }
    );
    public static final DefaultConfigData DIAMOND_SHOVEL = new DefaultConfigData(
            DIAMOND_RANK,
            "Drops from a diamond shovel",
            ExData.SHOVEL_TOOL,
            new String[]
                    {
                            "minecraft:grass",
                            "minecraft:mycelium", // I think these grow mushrooms over time?
                            "minecraft:dirt 1 2", // podzoi
                            "minecraft:cactus" // These sorts of things need a new mechanic, eventually.
                    }
    );

    // Non-tool ranks
    // Do we want an enum for this?  Seems overly complex.
//    public static final int EMPTY_HAND = 0;
//    public static final int NON_TOOL = 1;
//    public static final int SHEAR = 2;
//    public static final int FISHING_ROD = 3;
//    public static final int HOE = 4;
//    public static final int BUCKET = 5;

    // Environmental ranks
//    public static final int BLAST_RANK = 0;
//    public static final int BURN_RANK = 1;
//    public static final int WATER_RANK = 2;


    // Config Defaults
    // General pattern:
    // tier 1: basic material
    // tier 2: advanced material
    // tier 3: renewable material
    // tier 4: advanced renewable material
//    public static final String[] EMPTY_HAND_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:stick"
//            };
//    public static final String[] EMPTY_HAND_NON_TOOL_DROPS = new String[]
//            {
//                    "minecraft:planks",
//                    "minecraft:coal"
//            };
//    public static final String[] SHEAR_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:string"
//            };
//    public static final String[] FISHING_ROD_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:waterlily",
//                    "minecraft:slimeball",
//                    "minecraft:prismarine_shard"
//            };
//    public static final String[] HOE_DEFAULT_DROPS = new String[] // Would like to have tiers... but oh well.
//            {
//                    "minecraft:wheat_seeds",
//                    "minecraft:carrots",
//                    "minecraft:potatoes",
//                    "minecraft:beetroot_seeds"
//            };
//    public static final String[] BUCKET_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:water_bucket"
//            };
    // public static final String[] COMPASS_DEFAULT_DROPS = new String[] {}; // Low priority, drop filled in maps from other worlds.
    // public static final String[] SPONGE_DROPS = new String[] {};

    // Environmental Drops
//    public static final String[] BLAST_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:quartz",
//                    "minecraft:glowstone_dust",
//                    "minecraft:end_portal", // Eventually, we need this to be not so easy
//                    "minecraft:end_stone" // These need their own mechanic.  // Maybe they onle happen in the Bedrock End?
//                    //end rod?
//                    // chorus fruit?
//            };
//    public static final String[] BURN_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:nether_wart",
//                    "minecraft:netherrack" // similarly, then maybe these nly happen in The Bedrock Nether?
//                    // Need some way of getting wither skulls
//                    // Need some way of getting blaze rods
//                    // maybe zombie spawner on top of bedrock makes blazes?
//                    // Skeleton spawner makes wither skeletons?
//                    // Ghasts should form in a big enough area in the nether?
//            };
//    public static final String[] WATER_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:sponge 1 1",
//                    "minecraft:reeds",
//                    // Eventually, Bucket o' Liquid Bedrock
//            };
//    // Or wait!  Could we have sponge get used on the bedrock as a tool,
//    // And it gives you bedrock-laden sponge, which you can somehow make into liquid bedrock?
//
//    // This is probably the best place to get some of the blocks?
//    //public static final String[] GROWTH_DEFAULT_DROPS = new String[] {};
//    // I might put carrots and stuff in here instead of from hoeing the bedrock?
//    // public static final String[] FARMLAND_DEFAULT_DROPS = new String[] {};
//    // I'd like something here along the lines of 'if water is flowing next to bedrock,
//    // And it gives stone, there's a chance it'll be mossy stone?
//    // What about public static final String[] MOB_SPAWNER_DEFAULT... ?
//    // Some of these may just need to be changed to behaviours, not drops...
//    // public static final String[] ICE_DEFAULT... // So we can have snow form on the bedrock?
//    // Or should that be vice versa somehow?
//
//    // Pickaxe Drops
//    public static final String[] PICKAXE_WOOD_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:cobblestone"
//            };
//    public static final String[] PICKAXE_STONE_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:iron_ore",
//                    "minecraft:stone 1 1", // Granite
//                    "minecraft:stone 1 3", // Diorite
//                    "minecraft:stone 1 5", // Andesite
//            };
//    public static final String[] PICKAXE_IRON_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:lapis_ore",
//                    "minecraft:diamond_ore"
//            };
//    public static final String[] PICKAXE_DIAMOND_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:obsidian", // this isn't perfect, but we need a way to get it without lava, yet.
//                    "minecraft:gold_ore",
//                    "minecraft:redstone_ore",
//                    "minecraft:emerald_ore" // This may be unnecessary.  Depends on whether we can get villagers easily.  Could just force people to make them out of zombie villagers, and then trade with them...
//            };
//
//    // Axe Drops
//    public static final String[] AXE_WOOD_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:planks 1 1", // Spruce
//                    "minecraft:planks 1 2", // Birch
//                    "minecraft:planks 1 3", // Jungle
//                    "minecraft:planks 1 4", // Acacia
//                    "minecraft:planks 1 5" // Dark Oak
//            };
//    public static final String[] AXE_STONE_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:log",
//                    "minecraft:ice"
//            };
//    public static final String[] AXE_IRON_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:sapling",
//                    "minecraft:log 1 1", // Spruce
//                    "minecraft:log 1 2", // Birch
//                    "minecraft:log 1 3", // Jungle
//                    "minecraft:log 1 4", // Acacia
//                    "minecraft:log 1 5", // Dark Oak
//            };
//    public static final String[] AXE_DIAMOND_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:sapling 1 1", // Spruce
//                    "minecraft:sapling 1 2", // Birch
//                    "minecraft:sapling 1 3", // Jungle
//                    "minecraft:sapling 1 4", // Acacia
//                    "minecraft:sapling 1 5", // Dark Oak
//                    "minecraft:pumpkin",
//                    "minecraft:melon_block"
//            };
//
//    // Shovel Drops
//    public static final String[] SHOVEL_WOOD_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:dirt"
//            };
//    public static final String[] SHOVEL_STONE_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:sand",
//                    "minecraft:gravel",
//                    "minecraft:clay_ball"
//            };
//    public static final String[] SHOVEL_IRON_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:soul_sand", // Better if we add that it can only happen while it's burning.
//                    "minecraft:sand 1 1"
//            };
//    public static final String[] SHOVEL_DIAMOND_DEFAULT_DROPS = new String[]
//            {
//                    "minecraft:grass",
//                    "minecraft:mycelium", // I think these grow mushrooms over time?
//                    "minecraft:dirt 1 2", // podzoi
//                    "minecraft:cactus" // These sorts of things need a new mechanic, eventually.
//            };
}