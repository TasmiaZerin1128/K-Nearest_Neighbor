import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int k =5;

        ImageTrain train = new ImageTrain();

        Scanner scan = new Scanner(System.in);
        System.out.println("Pick your choice:\n1.Cat vs Dog  2.Summer vs Winter\nEnter 1 or 2: ");
        int choice = scan.nextInt();
        if(choice==1){
            train.setDatasetFolder(1);
        }
        else if (choice==2){
            train.setDatasetFolder(2);
        }

        System.out.println("Training Completed");

        File testImage = new File("src/Test_Images/test-8-w.jpg");
        ImageTest test = new ImageTest();

        if(choice==1){
            test.runTest(testImage,k,"cat","dog");
        }
        else if(choice==2){
            test.runTest(testImage,k,"summer","winter");
        }

    }
}
