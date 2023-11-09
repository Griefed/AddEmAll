package de.griefed.generation.generator;

import java.io.IOException;

public interface CodeGeneration {

    void run() throws IOException;

    /**
     * Create the ModBlocks-class inside the <code>your.group.modid.block</code>-package.
     * Depending on the modloader, the template from which this class is generated differs.
     * The functionality stays the same: The generated class allows for registering all custom blocks defined in
     * the <code>definitions/blocks.json</code>-file.<br>
     * <b>Do not manually edit the generated file!</b>
     *
     * @throws IOException when the file could not be created or edited.
     */
    void createModBlocksClass() throws IOException;

    /**
     * Create the ModItems-class inside the <code>your.group.modid.item</code>-package.
     * Depending on the modloader, the template from which this class is generated differs.
     * The functionality stays the same: The generated class allows for registering all custom items defined in
     * the <code>definitions/items.json</code>-file, as well as all items for any blocks defined in the
     * <code>definitions/blocks.json</code>-file.
     *
     * @throws IOException when the file could not be created or edited.
     */
    void createModItemsClass() throws IOException;

    /**
     * Create the modloader main-class housing the registration events, making sure that all blocks and items
     * actually get registered. The class gets created inside the <code>your.group.modid</code>-package.
     *
     * @throws IOException when the file could not be created or edited.
     */
    void createModloaderClass() throws IOException;

    /**
     * Update the <code>ModBlocks</code>-class to register all defined blocks.
     *
     * @throws IOException when the file could not be created or edited.
     */
    void updateModBlocksClass() throws IOException;

    /**
     * Update the <code>ModItems</code>-class to register all defined blocks and items.
     *
     * @throws IOException when the file could not be created or edited.
     */
    void updateModItemsClass() throws IOException;

    /**
     * Updates the modloaders main-class, more specifically the area inside said class which is reserved for updates
     * by this method. The generated code will ensure that all mods registered in the ModBlocks-class will get added
     * to the game by the respective modloader.
     *
     * @throws IOException when the file could not be created or edited.
     */
    void updateModloaderMain() throws IOException;

}
