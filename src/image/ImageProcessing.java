package image;

import java.util.Scanner;

public class ImageProcessing {

    private String imageName;
    private TweakedAPImage image;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        selectImage();

        image = new TweakedAPImage(imageName);
        image.draw();

    }

    public void selectImage() {
        System.out.println("Default Images : ");
        System.out.println("1. arch");
        System.out.println("2. butterfly1");
        System.out.println("3. koala");
        System.out.println("4. redMotorcycle");
        System.out.println("4. seagull");
        System.out.println("5. smokey");
        System.out.println("6. swan");
        System.out.println("For non-default images they must be stored in src/resources folder. (Must be a jpg file)");


        System.out.println("Enter the file name of the image: (Type a string) :");
        imageName = scanner.nextLine() + ".jpg";
    }

    public void presentImageProcessingOptions() {
        System.out.println("Select what processing you would like to do to the image (Type a number) :");

        System.out.println("1. Convert image to black and white");
        System.out.println("2. Convert image to grayscale");
        System.out.println("3. Convert image to luminance grayscale");
        System.out.println("4. Rotate image 90 degrees left");
        System.out.println("5. Rotate image 90 degrees right");
        System.out.println("6. Rotate image 180 degrees");
        System.out.println("7. Convert image to old-fashioned");
        System.out.println("8. Darken image");
        System.out.println("9. Brighten image");
        System.out.println("10. Apply color filter");
        System.out.println("11. Posterize image");
        System.out.println("12. Convert image to photographic negative");
        System.out.println("13. Sharpen image");
        System.out.println("14. Blur image");
        System.out.println("15. Shrink image");
        System.out.println("16. Enlarge image");

        int optionNum = scanner.nextInt();
        
        if (optionNum == 1) {
            convertBlackWhite();
        }
        if (optionNum == 2) {
            convertGrayscale();
        }
        if (optionNum == 3) {
            convertLuminanceGrayscale();

        }
        if (optionNum == 4) {
            rotateImage(-90);

        }
        if (optionNum == 5) {
            rotateImage(90);

        }
        if (optionNum == 6) {
            rotateImage(180);
        }
        if (optionNum == 7) {
            convertOldFashioned();
        }
        if (optionNum == 8) {
            darkenImage();
        }
        if (optionNum == 9) {
            brightenImage();
        }
        if (optionNum == 10) {
            applyColorFilter();
        }
        if (optionNum == 11) {
            posterizeImage();
        }
        if (optionNum == 12) {
            convertPhotographicNegative();
        }
        if (optionNum == 13) {
            sharpenImage();
        }
        if (optionNum == 14) {
            blurImage();
        }
        if (optionNum == 15) {
            shrinkImage();
        }
        if (optionNum == 16) {
            enlargenImage();
        }

    }

    public void convertBlackWhite() {

    }

    public void convertGrayscale() {

    }

    public void convertLuminanceGrayscale() {

    }

    public void convertOldFashioned() {

    }

    public void rotateImage(int degrees) {

    }

    public void darkenImage() {

    }

    public void brightenImage() {

    }

    public void applyColorFilter() {}

    public void posterizeImage() {}

    public void convertPhotographicNegative() {}

    public void sharpenImage() {}

    public void blurImage() {}

    public void shrinkImage() {}

    public void enlargenImage() {}
}
