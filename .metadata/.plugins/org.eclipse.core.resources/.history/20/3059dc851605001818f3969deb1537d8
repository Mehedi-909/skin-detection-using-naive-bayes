package imageTesting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ToGray {

    public static void main(String[] args) throws IOException {
        File file = new File("image/image (1).jpg");
        BufferedImage img = ImageIO.read(file);
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] pixel = new int[width][height];
        Raster raster = img.getData();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixel[i][j] = raster.getSample(i, j, 0);
            }
        }

        
        Color myWhite = new Color(255, 255, 255); // Color white
        int rgb = myWhite.getRGB();
        
        BufferedImage theImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int value = pixel[i][j] << 16 | pixel[i][j] << 8
                        | pixel[i][j];
                theImage.setRGB(i, j, rgb);
            }
        }

        File outputfile = new File("image/out.png");
        try {
            ImageIO.write(theImage, "png", outputfile);
        } catch (IOException e1) {

        }
}
}