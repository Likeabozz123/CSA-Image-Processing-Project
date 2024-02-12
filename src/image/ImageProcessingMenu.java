package image;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The ImageProcessingMenu class to deal with user input and displaying menus
 */
public class ImageProcessingMenu {

    public ImageProcessing imageProcessing;
    public boolean running;
    private final Scanner scanner = new Scanner(System.in);

    public ImageProcessingMenu(ImageProcessing imageProcessing) {
        this.imageProcessing = imageProcessing;
        this.running = true;
    }

    /**
     * Selects an image for processing.
     */
    public String selectImage() {
        System.out.println("Default Images : ");
        System.out.println("1. arch");
        System.out.println("2. butterfly1");
        System.out.println("3. koala");
        System.out.println("4. redMotorcycle");
        System.out.println("4. seagull");
        System.out.println("5. smokey");
        System.out.println("6. swan");
        System.out.println("For non-default images they must be stored in src/resources folder. (Must be a jpg file)");
        System.out.println("Invalid files or an empty input will automatically resort to smokey");

        System.out.println("Enter the file name of the image: (Type a string) :");

        return scanner.nextLine() + ".jpg";
    }

    /**
     * Presents choices for image processing to user.
     */
    public boolean presentImageProcessingOptions() {
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
        System.out.println("17. Quit");

        int optionNum = getNumberBetweenBounds(1, 17);

        if (optionNum == 1) {
            imageProcessing.convertBlackWhite();
        }
        if (optionNum == 2) {
            imageProcessing.convertGrayscale();
        }
        if (optionNum == 3) {
            imageProcessing.convertLuminanceGrayscale();
        }
        if (optionNum == 4) {
            imageProcessing.rotateLeft();
        }
        if (optionNum == 5) {
            imageProcessing.rotateRight();
        }
        if (optionNum == 6) {
            imageProcessing.flip();
        }
        if (optionNum == 7) {
            imageProcessing.convertOldFashioned();
        }
        if (optionNum == 8) {
            System.out.println("Enter how much you would like to darken your image by (only positive numbers):");

            int darkenAmount = getPositiveNumberInput();

            imageProcessing.darkenImage(darkenAmount);
        }
        if (optionNum == 9) {
            System.out.println("Enter how much you would like to brighten your image by (only positive numbers):");
            int brightenAmount = getPositiveNumberInput();

            imageProcessing.brightenImage(brightenAmount);
        }
        if (optionNum == 10) {
            System.out.println("Type the amount of red you would like to filter (0-255):");
            int redFilter = getNumberBetweenBounds(0, 255);

            System.out.println("Type the amount of green you would like to filter (0-255):");
            int greenFilter = getNumberBetweenBounds(0, 255);

            System.out.println("Type the amount of blue you would like to filter (0-255):");
            int blueFilter = getNumberBetweenBounds(0, 255);

            imageProcessing.applyColorFilter(redFilter, greenFilter, blueFilter);
        }
        if (optionNum == 11) {
            imageProcessing.posterizeImage();
        }
        if (optionNum == 12) {
            imageProcessing.convertPhotographicNegative();
        }
        if (optionNum == 13) {
            System.out.println("Enter the degree of which you would like the image to be sharpened by:");
            int degree = getPositiveNumberInput();

            System.out.println("Enter threshold:");
            int threshold = getPositiveNumberInput();

            imageProcessing.sharpenImage(degree, threshold);
        }
        if (optionNum == 14) {
            imageProcessing.blurImage();
        }
        if (optionNum == 15) {
            System.out.println("Enter how much you would like the image to be shrunk by (multiplicative) (only integers):");
            int shrinkFactor = getPositiveNumberInput();

            imageProcessing.shrinkImage(shrinkFactor);
        }
        if (optionNum == 16) {
            System.out.println("Enter how much you would like the image to be enlarged by (multiplicative) (only integers):");
            int enlargenFactor = getPositiveNumberInput();

            imageProcessing.enlargenImage(enlargenFactor);
        }
        if (optionNum == 17) running = false;

        return running;
    }

    /**
     * Gets a positive number input, but with error handling
     * @return a valid input
     */
    public int getPositiveNumberInput() {
        int num = 10;

        boolean validInput = false;
        while (!validInput) {
            try {
                num = scanner.nextInt();
                while (num < 0) {
                    System.out.println("Please re-enter a valid number:");
                    num = scanner.nextInt();
                }
                validInput = true;
            } catch (InputMismatchException exception) {
                System.out.println("Invalid input please try again.");
                scanner.next(); // consumes the invalid token
            }
        }
        return num;
    }

    /**
     * Gets a number input between two bounds, but with error handling
     * @return a valid input between the bounds
     */
    public int getNumberBetweenBounds(int startBound, int endBound) {

        int num = 10;

        boolean validInput = false;
        while (!validInput) {
            try {
                num = scanner.nextInt();
                while (num < startBound || num > endBound) {
                    System.out.println("Please re-enter a valid number:");
                    num = scanner.nextInt();
                }
                validInput = true;
            } catch (InputMismatchException exception) {
                System.out.println("Invalid input please try again.");
                scanner.next(); // consumes the invalid token
            }
        }
        return num;
    }


}
