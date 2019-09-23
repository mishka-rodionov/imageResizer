import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String sw360dp = "drawable-sw360dp";
    private static Integer smallestWidth = 360;
    private static Integer highestXhdpi = 1560;
    private static Integer highestXxhdpi = 2520;
    private static Integer highestXxxhdpi = 3040;
    private static String[] dpi = {"xhdpi", "xxhdpi", "xxxhdpi"};
    private static Map<String, Integer> dpiMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        dpiMap.put("xhdpi", highestXhdpi);
        dpiMap.put("xxhdpi", highestXxhdpi);
        dpiMap.put("xxxhdpi", highestXxxhdpi);
        File orignalImage = new File("drawable\\launch.png");

//        String newFile = orignalImage.getAbsolutePath().substring(0,orignalImage.getAbsolutePath().lastIndexOf("."))+"_"+w+"x"+h+".png";
//        String newFile = orignalImage.getParent() + "\\drawable-sw360dp\\launche.png";

        String newFile = orignalImage.getParent() + "\\drawable-sw360dp";

//        String newDiretory = orignalImage.getParent() + "\\drawable-sw360dp";

        try {
            BufferedImage origBuffImg = ImageIO.read(orignalImage);
            for (String density : dpi) {
                int newImageWidth;
                int newImageHeight;
                float heightFactor = (float) (origBuffImg.getHeight()) / (float) (dpiMap.get(density));
                newImageWidth = Math.round(origBuffImg.getWidth() / heightFactor);
                newImageHeight = dpiMap.get(density);

                System.out.println("original width = " + origBuffImg.getWidth());
                System.out.println("original height = " + origBuffImg.getHeight());
                System.out.println("new image width = " + newImageWidth);
                System.out.println("new image height = " + newImageHeight);
                System.out.println("height factor for density " + density + " is = " + heightFactor);

                String newDirectory = sw360dp + "-" + density;
                newFile = newDirectory + "\\launch.png";
                Files.createDirectories(Paths.get(newDirectory));
                Thumbnails.of(orignalImage)
                        .size(newImageWidth, newImageHeight)
                        .toFile(new File(newFile));
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
