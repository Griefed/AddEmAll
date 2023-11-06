package de.griefed.generation.blocks;

public class BlockDefinition {

    private String id;
    private String translation;
    private String material;
    private int strength;
    private int lightLevel;
    private int explosionResistance;
    private boolean requiresCorrectTool;
    private boolean instabreak;

    public String getId() {
        return id;
    }

    public String getTranslation() {
        return translation;
    }

    public String getMaterial() {
        return material;
    }

    public int getStrength() {
        return strength;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public int getExplosionResistance() {
        return explosionResistance;
    }

    public boolean isRequiresCorrectTool() {
        return requiresCorrectTool;
    }

    public boolean isInstabreak() {
        return instabreak;
    }
}
