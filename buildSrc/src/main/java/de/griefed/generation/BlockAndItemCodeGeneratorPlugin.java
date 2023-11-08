package de.griefed.generation;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Generates Forge, Fabric and Quilt block-implementations from block-definitions in a JSON-file.<br>
 * See <code>definitions/blocks.json</code><br>
 * Take note that textures for blocks must be present in <code>definitions/blocks</code> and the name of the textures
 * must match the ID of the block.
 */
public class BlockAndItemCodeGeneratorPlugin implements Plugin<Project> {
    public void apply(Project project) {
        project.getTasks().register("blockAndItemCodeGen", GenerateBlocksAndItemsCode.class);
    }
}
