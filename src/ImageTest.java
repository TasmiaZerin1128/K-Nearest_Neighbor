import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ImageTest {
    ImageTrain trainer = new ImageTrain();

    List<Euclid_Distance> euclidean_distance = new ArrayList<Euclid_Distance>();  // store distance and dataset

    public static List<Integer> testImageClr = new ArrayList<Integer>();

    int min_height = 100;
    int min_width = 100;

    double total_pixel_clr = 0.0;
    int index =0;
    int indexTest = 0;

    private void calculateDistance(){
            distance_ind_dataset(trainer.imageClr_dataset1, trainer.imgDataset1,1);
            index=0;         //reset
            distance_ind_dataset(trainer.imageClr_dataset2, trainer.imgDataset2,2);
    }

    private void distance_ind_dataset(List<Integer> dataset,int imgCountDatabase, int type) {
        for(int imgCount=0; imgCount<imgCountDatabase; imgCount++) {
            for (int h = 0; h < min_height; h++) {
                for (int w = 0; w < min_width; w++) {
                    total_pixel_clr += Math.pow((dataset.get(index) - testImageClr.get(indexTest)), 2);
                    total_pixel_clr += Math.pow((dataset.get(index + 1) - testImageClr.get(indexTest + 1)), 2);
                    total_pixel_clr += Math.pow((dataset.get(index + 2) - testImageClr.get(indexTest + 2)), 2);
                    index += 3;
                }
            }
            Euclid_Distance e = new Euclid_Distance(Math.sqrt(total_pixel_clr), type);
            euclidean_distance.add(e);
            total_pixel_clr = 0.0;
            indexTest = 0;
        }
    }


    private void extractColour(File image) throws IOException {
        BufferedImage imgI = ImageIO.read(image);
        BufferedImage imgI_resized = new BufferedImage(min_width,min_height, imgI.getType());

        for (int h = 0; h < imgI_resized.getHeight(); h++) {
            for (int w = 0; w < imgI_resized.getWidth(); w++) {
                int pixelI = imgI.getRGB(w,h);
                Color colorI = new Color(pixelI);

                testImageClr.add(colorI.getRed());
                testImageClr.add(colorI.getGreen());
                testImageClr.add(colorI.getBlue());
            }
        }
    }

    private void checkOutputforK(int K, String typeName1, String typeName2){
        int set1=0;
        int set2 = 0;

        for(int i=0;i<K;i++){
            if((euclidean_distance.get(i).dataset_type)==1){
                set1++;
            }
            else{
                set2++;
            }
        }

        if(set1>set2) {
            System.out.println("\n\nThe test image is " + typeName1);
        }
        else{
            System.out.println("\n\nThe test image is "+ typeName2);
        }
    }

    public void runTest(File image, int K, String set1, String set2) throws IOException {

        extractColour(image);
        calculateDistance();

        Collections.sort(euclidean_distance, new Comparator<Euclid_Distance>() {
            @Override
            public int compare(Euclid_Distance o1, Euclid_Distance o2) {
                return o1.distance.compareTo(o2.distance);
            }
        });
        System.out.println("After Sort");

        for(int i = 0; i< euclidean_distance.size(); i++){
            System.out.println(euclidean_distance.get(i).distance + " " + euclidean_distance.get(i).dataset_type);
        }
        checkOutputforK(K, set1,set2);

    }

}
