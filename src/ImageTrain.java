import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageTrain {

    public static List<Integer> imageClr_dataset1 = new ArrayList<Integer>();
    public static List<Integer> imageClr_dataset2 = new ArrayList<Integer>();

    public static int imgDataset1 = 0;
    public static int imgDataset2 = 0;

    int min_height = 100;
    int min_width = 100;


    private void extractColour(File image, int dataset) throws IOException {

        BufferedImage imgI = ImageIO.read(image);
        BufferedImage imgI_resized = new BufferedImage(min_width,min_height, imgI.getType());

        for (int h = 0; h < imgI_resized.getHeight(); h++) {
            for (int w = 0; w < imgI_resized.getWidth(); w++) {
                int pixelI = imgI.getRGB(w,h);
                Color colorI = new Color(pixelI);

                if(dataset==1) {
                    imageClr_dataset1.add(colorI.getRed());
                    imageClr_dataset1.add(colorI.getGreen());
                    imageClr_dataset1.add(colorI.getBlue());
                }
                else{
                    imageClr_dataset2.add(colorI.getRed());
                    imageClr_dataset2.add(colorI.getGreen());
                    imageClr_dataset2.add(colorI.getBlue());
                }
            }
        }
    }

    public void findAllFilesinFolder(File folder, int dataset) throws IOException {
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){          // check file or directory
                if(dataset==1){
                    imgDataset1++;
                }
                else{
                    imgDataset2++;
                }

                System.out.println("Image processing");
                extractColour(file,dataset);
            }
            else{
                return;
            }
        }
    }

    public void setDatasetFolder(int choice) throws IOException {
        File type1 = null,type2 = null;

        if(choice==1){
            type1 = new File("src/cat_vs_dog/cats");
            type2 = new File("src/cat_vs_dog/dogs");
        }
        else if(choice==2){
            type1 = new File("src/summer_vs_winter/summer");

            type2 = new File("src/summer_vs_winter/winter");
        }
        findAllFilesinFolder(type1,1);
        findAllFilesinFolder(type2,2);

    }
}
