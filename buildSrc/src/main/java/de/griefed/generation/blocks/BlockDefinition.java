package de.griefed.generation.blocks;

public class BlockDefinition {

    private String id;
    private String translation;
    private String material;
    private String soundType;
    private int strengthOne;
    private int strengthTwo;
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

    public String getSoundType() { return soundType; }

    public int getStrengthOne() {
        return strengthOne;
    }

    public int getStrengthTwo() {
        return strengthTwo;
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
