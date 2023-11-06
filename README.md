## AddEmAll

<!-- Mod page badges. Change the project ids and hyperlinks for correct data. -->
<!--
[![](http://cf.way2muchnoise.eu/full_415747_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/crumbs)
[![](https://modrinth-utils.vercel.app/api/badge/downloads/?id=crumbs&logo=true)](https://modrinth.com/mod/crumbs)
[![](http://cf.way2muchnoise.eu/versions/415747.svg)](https://www.curseforge.com/minecraft/mc-mods/crumbs)
-->

Assortment of blocks I want to use.

## What does this mod do?

This mod adds an assortment of blocks I would like to use in Minecraft. Mainly blocks inspired by the aesthetic of Half Life 2's Citadel,
as well as other things good for decorative purposes.

## Other Information:

- Report Bugs at https://github.com/Griefed/AddEmAll/issues
- You can include this mod in your modpack as long as you don't claim the mod as your own.

# Development

## IntelliJ IDEA
This guide will show how to import the MultiLoader Template into IntelliJ IDEA. The setup process is roughly equivalent to setting up Forge and Fabric independently and should be very familiar to anyone who has worked with their MDKs.

1. Clone or download this repository to your computer.
2. Open the template's root folder as a new project in IDEA. This is the folder that contains this README file and the gradlew executable.
3. If your default JVM/JDK is not Java 17 you will encounter an error when opening the project. This error is fixed by going to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`and changing the value to a valid Java 17 JVM. You will also need to set the Project SDK to Java 17. This can be done by going to `File > Project Structure > Project SDK`. Once both have been set open the Gradle tab in IDEA and click the refresh button to reload the project.
4. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Common > Tasks > vanilla gradle > decompile`. Run this task to decompile Minecraft.
5. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Forge > Tasks > forgegradle runs > genIntellijRuns`. Run this task to set up run configurations for Forge.
6. Open your Run/Debug Configurations. Under the Application category there should now be options to run Forge and Fabric projects. Select one of the client options and try to run it.
7. Assuming you were able to run the game in step 7 your workspace should now be set up.

## Development Guide
When using this template the majority of your mod is developed in the Common project. The Common project is compiled against the vanilla game and is used to hold code that is shared between the different loader-specific versions of your mod. The Common project has no knowledge or access to ModLoader specific code, apis, or concepts. Code that requires something from a specific loader must be done through the project that is specific to that loader, such as the Forge or Fabric project.

Loader specific projects such as the Forge and Fabric project are used to load the Common project into the game. These projects also define code that is specific to that loader. Loader specific projects can access all of the code in the Common project. It is important to remember that the Common project can not access code from loader specific projects.
