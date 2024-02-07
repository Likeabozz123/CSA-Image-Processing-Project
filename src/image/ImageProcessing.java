package image;

import images.Pixel;

import java.util.Scanner;

public class ImageProcessing {

    private String imageName;
    private TweakedAPImage image;
    private boolean running = true;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        selectImage();

        image = new TweakedAPImage(imageName);

        while (running) {
            presentImageProcessingOptions();
            image.draw();
        }

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
        System.out.println("17. Quit");

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
            rotateLeft();

        }
        if (optionNum == 5) {
            rotateRight();

        }
        if (optionNum == 6) {
            flip();
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
        if (optionNum == 17) running = false;

    }

    public void convertBlackWhite() {
        for (Pixel pixel : image) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            int avgColors = (red + green + blue) / 3;

            if (avgColors < 128) {
                pixel.setRed(0);
                pixel.setGreen(0);
                pixel.setBlue(0);
            } else {
                pixel.setRed(255);
                pixel.setGreen(255);
                pixel.setBlue(255);
            }

        }
    }

    public void convertGrayscale() {
        for (Pixel pixel : image) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            int avgColors = (red + green + blue) / 3;
            pixel.setRed(avgColors);
            pixel.setGreen(avgColors);
            pixel.setBlue(avgColors);
        }

    }

    // not sure if this fully works tbh
    public void convertLuminanceGrayscale() {
        for (Pixel pixel : image) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            int luminanceAvg = (int) (red * .299) + (int) (green * .587) + (int) (blue * 0.114);
            pixel.setRed(luminanceAvg);
            pixel.setGreen(luminanceAvg);
            pixel.setBlue(luminanceAvg);
        }
    }

    public void convertOldFashioned() {

    }

    public void rotateLeft() {
        TweakedAPImage rotatedImage = new TweakedAPImage(image.getHeight(), image.getWidth());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedImage.setPixel(y, x, originalPixel);
            }
        }
        image = rotatedImage;
    }

    public void rotateRight() {
        TweakedAPImage rotatedImage = new TweakedAPImage(image.getHeight(), image.getWidth());
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel originalPixel = image.getPixel(x, y);
                rotatedImage.setPixel(rotatedImage.getWidth() - 1 - y, rotatedImage.getHeight() - 1 - x, originalPixel);
            }
        }
        image = rotatedImage;
    }

    public void flip() {
        TweakedAPImage flippedImage = new TweakedAPImage(image.getWidth(), image.getHeight());
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel orignalImagePixel = image.getPixel(image.getWidth() - 1 - x, image.getHeight() - 1 - y);
                flippedImage.setPixel(x, y, orignalImagePixel);
            }
        }
        image = flippedImage;
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
