package de.griefed.generation.blocks;

public class BlockDefinition {

    public BlockDefinition(String id,
                           String translation,
                           String material,
                           String soundType,
                           int strengthOne,
                           int strengthTwo,
                           int lightLevel,
                           int explosionResistance,
                           boolean requiresCorrectTool,
                           boolean instabreak,
                           boolean generateSlab,
                           boolean generateStair
    ) {
        this.id = id;
        this.translation = translation;
        this.material = material;
        this.soundType = soundType;
        this.strengthOne = strengthOne;
        this.strengthTwo = strengthTwo;
        this.lightLevel = lightLevel;
        this.explosionResistance = explosionResistance;
        this.requiresCorrectTool = requiresCorrectTool;
        this.instabreak = instabreak;
        this.generateSlab = generateSlab;
        this.generateStair = generateStair;
    }

    public BlockDefinition() {
    }

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
    private boolean generateSlab;
    private boolean generateStair;

    public String getId() {
        return id;
    }

    public String getTranslation() {
        return translation;
    }

    public String getMaterial() {
        return material;
    }

    public String getSoundType() {
        return soundType;
    }

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

    public boolean generateSlab() {
        return generateSlab;
    }

    public boolean generateStair() {
        return generateStair;
    }
}
