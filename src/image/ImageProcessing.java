package image;

import java.util.Scanner;

public class ImageProcessing {

    private String imageName;
    private TweakedAPImage image;

    public void run() {
        presentImageOptions();

        image = new TweakedAPImage(imageName);
        image.draw();
    }

    public void presentImageOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Default Images : ");
        System.out.println("1. arch");
        System.out.println("2. butterfly1");
        System.out.println("3. koala");
        System.out.println("4. redMotorcycle");
        System.out.println("4. seagull");
        System.out.println("5. smokey");
        System.out.println("6. swan");
        System.out.println("For non-default images they must be stored in src/resources folder.");


        System.out.println("File name of the image (must be jpg file type) :");
        imageName = scanner.nextLine() + ".jpg";
    }

}
