package image;

import images.Pixel;

import java.util.Random;
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
            sharpenImage(20, 10);
        }
        if (optionNum == 14) {
            blurImage();
        }
        if (optionNum == 15) {
            shrinkImage(3);
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
        for (Pixel pixel : image) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();

            if (red < 63) {
                red = (int) (red * 1.1);
                blue = (int) (blue * 0.9);
            }
            if (red < 192) {
                red = (int) (red * 1.15);
                blue = (int) (blue * 0.85);
            }
            if (red > 192) {
                red = Math.min((int) (red * 1.08), 255);
                blue = (int) (blue * 0.93);
            }

            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
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

    public void brightenImage() {
        for (Pixel pixel : image) {
            int red = Math.min(pixel.getRed() + 50, 255);
            int green = Math.min(pixel.getGreen() + 50, 255);
            int blue = Math.min(pixel.getBlue() + 50, 255);
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
    }

    public void darkenImage() {
        for (Pixel pixel : image) {
            int red = Math.max(0, pixel.getRed() - 50);
            int green = Math.max(0, pixel.getGreen() - 50);
            int blue = Math.max(0, pixel.getBlue() - 50);
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
    }

    public void applyColorFilter() {

        for (Pixel pixel : image) {
            int red = Math.min(pixel.getRed() + 20, 255);
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
    }

    public void posterizeImage() {
        Random random = new Random();
        int newRed = random.nextInt(256);
        int newGreen = random.nextInt(256);
        int newBlue = random.nextInt(256);
        for (Pixel pixel : image) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            int avgColors = (red + green + blue) / 3;

            if (avgColors > 128) {
                pixel.setRed(255);
                pixel.setGreen(255);
                pixel.setBlue(255);
            } else {

                pixel.setRed(newRed);
                pixel.setGreen(newGreen);
                pixel.setBlue(newBlue);
            }

        }

    }

    public void convertPhotographicNegative() {
        convertGrayscale();
        for (Pixel pixel : image) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            pixel.setRed(255 - red);
            pixel.setGreen(255 - green);
            pixel.setBlue(255 - blue);
        }

    }

    public void sharpenImage(int degree, int threshold) {
        for (int y = 0; y < image.getHeight() - 1; y++) {
            for (int x = 1; x < image.getWidth(); x++) {
                Pixel cornerPixel = image.getPixel(x - 1, y);
                Pixel rightPixel = image.getPixel(x, y);
                Pixel bottomPixel = image.getPixel(x, y + 1);

                int cornerAvg = (cornerPixel.getRed() + cornerPixel.getGreen() + cornerPixel.getBlue()) / 3;
                int rightAvg = (rightPixel.getRed() + rightPixel.getGreen() + rightPixel.getBlue()) / 3;
                int bottomAvg = (bottomPixel.getRed() + bottomPixel.getGreen() + bottomPixel.getBlue()) / 3;

                if (Math.abs(rightAvg - cornerAvg) <= threshold || Math.abs(rightAvg - bottomAvg) <= threshold) {
                    int red = rightPixel.getRed();
                    int green = rightPixel.getGreen();
                    int blue = rightPixel.getBlue();

                    rightPixel.setRed(Math.min(red + degree, 255));
                    rightPixel.setGreen(Math.min(green + degree, 255));
                    rightPixel.setBlue(Math.min(blue + degree, 255));
                }
            }
        }
    }

    public void blurImage() {

        for (int y = 1; y < image.getHeight() - 2; y++) {
            for (int x = 1; x < image.getWidth() - 2; x++) {
                Pixel topPixel = image.getPixel(x, y - 1);
                Pixel bottomPixel = image.getPixel(x, y + 1);
                Pixel rightPixel = image.getPixel(x + 1, y);
                Pixel leftPixel = image.getPixel(x - 1, y);

                int topAvg = (topPixel.getRed() + topPixel.getGreen() + topPixel.getBlue()) / 3;
                int bottomAvg = (bottomPixel.getRed() + bottomPixel.getGreen() + bottomPixel.getBlue()) / 3;
                int rightAvg = (rightPixel.getRed() + rightPixel.getGreen() + rightPixel.getBlue()) / 3;
                int leftAvg = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue()) / 3;

                int totalAvg = (topAvg + bottomAvg + rightAvg + leftAvg) / 4;

                image.getPixel(x, y).setRed(totalAvg);
                image.getPixel(x, y).setGreen(totalAvg);
                image.getPixel(x, y).setBlue(totalAvg);


            }
        }

    }

    public void shrinkImage(int shrinkFactor) {
        int newWidth = image.getWidth() / shrinkFactor;
        int newHeight = image.getHeight() / shrinkFactor;

        TweakedAPImage shrunkImage = new TweakedAPImage(newWidth, newHeight);

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;

                for (int dy = 0; dy < shrinkFactor; dy++) {
                    for (int dx = 0; dx < shrinkFactor; dx++) {
                        Pixel original = image.getPixel(x * shrinkFactor + dx, y * shrinkFactor + dy);
                        totalRed += original.getRed();
                        totalGreen += original.getGreen();
                        totalBlue += original.getBlue();
                    }
                }

                int shrunkRed = totalRed / (shrinkFactor * shrinkFactor);
                int shrunkGreen = totalGreen / (shrinkFactor * shrinkFactor);
                int shrunkBlue = totalBlue / (shrinkFactor * shrinkFactor);


                Pixel shrunkPixel = new Pixel(shrunkRed, shrunkGreen, shrunkBlue);
                shrunkImage.setPixel(x, y, shrunkPixel);
            }
        }
        image = shrunkImage;
    }

    public void enlargenImage() {
    }
}
