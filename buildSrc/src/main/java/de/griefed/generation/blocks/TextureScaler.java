package de.griefed.generation.blocks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextureScaler {
    public static BufferedImage getScaledInstance(BufferedImage sourceImage) {
        Dimension imgSize = new Dimension(sourceImage.getWidth(), sourceImage.getHeight());
        double widthRatio;
        Image scaledImage;
        BufferedImage output;
        int newWidth = 16;
        int newHeight;
        widthRatio = (double) newWidth / imgSize.width;
        newHeight = (int) (sourceImage.getHeight() * widthRatio);
        while (newHeight % 16 != 0) {
            newHeight++;
        }
        scaledImage = sourceImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        output = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        output.getGraphics().drawImage(scaledImage, 0, 0, null);
        return output;
    }
}
