## AddEmAll

<!-- Mod page badges. Change the project ids and hyperlinks for correct data. -->

[![](http://cf.way2muchnoise.eu/full_935081_downloads.svg)](https://legacy.curseforge.com/minecraft/mc-mods/addemall)
[![](https://modrinth-utils.vercel.app/api/badge/downloads/?id=usDxEvVr&logo=true)](https://modrinth.com/mod/addemall)
[![](http://cf.way2muchnoise.eu/versions/935081.svg)](https://legacy.curseforge.com/minecraft/mc-mods/addemall)


## What does this mod do?

This mod adds an assortment of blocks and items.
Mainly blocks inspired by the aesthetic of Half Life 2's Citadel, as well as other things good for decorative purposes.

Nothing big. Nothing fancy. Nothing complicated.

All blocks / items in this mod are currently only available via creative mode.

![showcase](https://i.griefed.de/upload/2023/11/09/20231109115332-42ea9451.png)

## Other Information:

- Report Bugs at https://github.com/Griefed/AddEmAll/issues
- You can include this mod in your modpack as long as you don't claim the mod as your own.

# Development

## Future

In the future I would like to add:
- custom fluids to resemble the radioactive water in Half-Life (2), with the sound and toxicity.
- Either recipes to acquire the blocks in survival, or an item which consumes the material defined in the block to place said block in the world

Help with either of these would be greatly appreciated! I would love to keep these features in the [Common](Common/src/main/java/de/griefed/addemall)-package as much as possible.

Currently, no other Minecraft, Forge or Fabric version support is planned. I mainly want to use the blocks in this mod in a modpack I enjoy.
If you want support for other versions, feel free to fork this project, implement your changes, and submit a pull request!

## IntelliJ IDEA

1. Clone or download this repository to your computer.
2. Open the repository's root folder as a new project in IDEA. This is the folder that contains this README file and the gradlew executable.
3. If your default JVM/JDK is not Java 17 you will encounter an error when opening the project. This error is fixed by going to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`and changing the value to a valid Java 17 JVM. You will also need to set the Project SDK to Java 17. This can be done by going to `File > Project Structure > Project SDK`. Once both have been set open the Gradle tab in IDEA and click the refresh button to reload the project.
4. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Common > Tasks > vanilla gradle > decompile`. Run this task to decompile Minecraft.
5. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Forge > Tasks > forgegradle runs > genIntellijRuns`. Run this task to set up run configurations for Forge.
6. Open your Run/Debug Configurations. Under the Application category there should now be options to run Forge and Fabric projects. Select one of the client options and try to run it.
7. Assuming you were able to run the game in step 7 your workspace should now be set up.

## Development Guide

The majority of the mod is developed in the Common project. The Common project is compiled against the vanilla game and is used to hold code that is shared between the different loader-specific versions of your mod. The Common project has no knowledge or access to ModLoader specific code, apis, or concepts. Code that requires something from a specific loader must be done through the project that is specific to that loader, such as the Forge or Fabric project.
Loader specific projects such as the Forge and Fabric project are used to load the Common project into the game. These projects also define code that is specific to that loader. Loader specific projects can access all of the code in the Common project. It is important to remember that the Common project can not access code from loader specific projects.

## Adding blocks

In order to add a new block, all you have to do is:

1. Edit the [definitions/blocks.json](definitions/blocks.json) and add a new entry for your block. Make sure to provide valid JSON.
Example:
```json
{
    "id": "some_block",
    "translation": "Some Block",
    "material": "DIRT",
    "soundType": "GRASS",
    "strengthOne": 2,
    "strengthTwo": 2,
    "lightLevel": 0,
    "explosionResistance": 0,
    "requiresCorrectTool": false,
    "instabreak": false
  }
```
2. Provide the texture you want your block to use in [definitions/assets/blocks](definitions/assets/blocks)
   1. Textures get automatically scaled down to 16x16
   2. The filename must match the id of the block added in `1.`, i.e. `some_block.png`
   3. Textures whose height is larger than their width will get scaled to 16x multiples of 16 automatically, so 16x17 will result in 16x32.
   4. Textures whose height is larger than their width will automatically have a basic animation
    ```json
    {
      "animation": {}
    }
   ```
3. Run `blockAndItemCodeGen` in `Tasks -> other`

When adding new blocks, see the list below for available material- and soundtypes.

| Material                    | Sound                |
|-----------------------------|----------------------|
| SAND                        | STONE                |
| AIR                         | AMETHYST             |
| WOOL                        | AMETHYST_CLUSTER     |
| STONE                       | ANCIENT_DEBRIS       |
| AMETHYST                    | ANVIL                |
| BAMBOO                      | AZALEA               |
| BAMBOO_SAPLING              | AZALEA_LEAVES        |
| BARRIER                     | BAMBOO               |
| BUBBLE_COLUMN               | BAMBOO_SAPLING       |
| BUILDABLE_GLASS             | BASALT               |
| CACTUS                      | BIG_DRIPLEAF         |
| CAKE                        | BONE_BLOCK           |
| CLAY                        | CALCITE              |
| CLOTH_DECORATION            | CANDLE               |
| DECORATION                  | CAVE_VEINS           |
| DIRT                        | CHAIN                |
| EGG                         | COPPER               |
| EXPLOSIVE                   | CROP                 |
| FIRE                        | DEEPSLATE            |
| FROGLIGHT                   | DEEPSLATE_BRICKS     |
| FROGSPAWN                   | DEEPSLATE_TILES      |
| GLASS                       | DRIPSTONE_BLOCK      |
| GRASS                       | FLOWERING_AZALEA     |
| HEAVY_METAL                 | FROGLIGHT            |
| ICE                         | FROGSPAWN            |
| ICE_SOLID                   | FUNGUS               |
| LAVA                        | GILDED_BLACKSTONE    |
| LEAVES                      | GLASS                |
| METAL                       | GLOW_LICHEN          |
| MOSS                        | GRASS                |
| NETHER_WOOD                 | GRAVEL               |
| PISTON                      | HANGING_ROOTS        |
| PLANT                       | HARD_CROP            |
| PORTAL                      | HONEY_BLOCK          |
| POWDER_SNOW                 | LADDER               |
| REPLACEABLE_FIREPROOF_PLANT | LANTERN              |
| REPLACEABLE_PLANT           | LARGE_AMETHYST_BUD   |
| REPLACEABLE_WATER_PLANT     | LILY_PAD             |
| SCULK                       | LODESTONE            |
| SHULKER_SHELL               | MANGROVE_ROOTS       |
| SNOW                        | MEDIUM_AMETHYST_BUD  |
| SPONGE                      | METAL                |
| STRUCTURAL_AIR              | MOSS                 |
| TOP_SNOW                    | MOSS_CARPET          |
| VEGETABLE                   | MUD                  |
| WATER                       | MUD_BRICKS           |
| WATER_PLANT                 | MUDDY_MANGROVE_ROOTS |
| WEB                         | NETHER_BRICKS        |
| WOOD                        | NETHER_GOLD_ORE      |
|                             | NETHER_ORE           |
|                             | NETHER_SPROUTS       |
|                             | NETHER_WART          |
|                             | NETHERITE_BLOCK      |
|                             | NETHERRACK           |
|                             | NYLIUM               |
|                             | PACKED_MUD           |
|                             | POINTED_DRIPSTONE    |
|                             | POLISHED_DEEPSLATE   |
|                             | POWDER_SNOW          |
|                             | ROOTED_DIRT          |
|                             | ROOTS                |
|                             | SAND                 |
|                             | SCAFFOLDING          |
|                             | SCULK                |
|                             | SCULK_CATALYST       |
|                             | SCULK_SENSOR         |
|                             | SCULK_SHRIEKER       |
|                             | SCULK_VEIN           |
|                             | SHROOMLIGHT          |
|                             | SLIME_BLOCK          |
|                             | SMALL_AMETHYST_BUD   |
|                             | SMALL_DRIPLEAF       |
|                             | SNOW                 |
|                             | SOUL_SAND            |
|                             | SOUL_SOIL            |
|                             | SPORE_BLOSSOM        |
|                             | STEM                 |
|                             | SWEET_BERRY_BUSH     |
|                             | TUFF                 |
|                             | TWISTING_VINES       |
|                             | VINE                 |
|                             | WART_BLOCK           |
|                             | WEEPING_VINES        |
|                             | WET_GRASS            |
|                             | WOOD                 |
|                             | WOOL                 |