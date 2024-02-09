package image;

import images.APImage;
import images.Pixel;

import java.util.Random;
import java.util.Scanner;

public class ImageProcessing {

    private String imageName;
    private APImage processedImage;
    private boolean running = true;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        selectImage();

        APImage originalImage = new APImage("src/resources/" + imageName);
        processedImage = originalImage.clone();
        originalImage.draw();

        do {
            APImage deletedImage = processedImage;
            processedImage = originalImage.clone();
            presentImageProcessingOptions();
            processedImage.draw();
            deletedImage.dispose();
        } while (running);

        if (!running) System.exit(0); // bhatanagar allowed this since theres no really clean alternative
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
            System.out.println("Enter how much you would like to darken your image by:");
            int darkenAmount = scanner.nextInt();
            darkenImage(darkenAmount);
        }
        if (optionNum == 9) {
            System.out.println("Enter how much you would like to brighten your image by:");
            int brightenAmount = scanner.nextInt();
            brightenImage(brightenAmount);
        }
        if (optionNum == 10) {
            System.out.println("Type the amount of red you would like to filter (0-255):");
            int redFilter = scanner.nextInt();
            System.out.println("Type the amount of green you would like to filter (0-255):");
            int greenFilter = scanner.nextInt();
            System.out.println("Type the amount of blue you would like to filter (0-255):");
            int blueFilter = scanner.nextInt();
            applyColorFilter(redFilter, greenFilter, blueFilter);
        }
        if (optionNum == 11) {
            posterizeImage();
        }
        if (optionNum == 12) {
            convertPhotographicNegative();
        }
        if (optionNum == 13) {
            System.out.println("Enter the degree of which you would like the image to be sharpened by:");
            int degree = scanner.nextInt();
            System.out.println("Enter threshold:");
            int threshold = scanner.nextInt();
            sharpenImage(degree, threshold);
        }
        if (optionNum == 14) {
            blurImage();
        }
        if (optionNum == 15) {
            System.out.println("Enter how much you would like the image to be shrunk by (multiplicative):");
            double shrinkFactor = scanner.nextDouble();
            shrinkImage(shrinkFactor);
        }
        if (optionNum == 16) {
            System.out.println("Enter how much you would like the image to be enlarged by (multiplicative):");
            double enlargenFactor = scanner.nextDouble();
            enlargenImage(enlargenFactor);
        }
        if (optionNum == 17) running = false;

    }

    public void convertBlackWhite() {
        for (Pixel pixel : processedImage) {
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
        for (Pixel pixel : processedImage) {
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
        for (Pixel pixel : processedImage) {
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
        for (Pixel pixel : processedImage) {
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
        APImage rotatedImage = new APImage(processedImage.getHeight(), processedImage.getWidth());
        for (int y = 0; y < processedImage.getHeight(); y++) {
            for (int x = 0; x < processedImage.getWidth(); x++) {
                Pixel originalPixel = processedImage.getPixel(x, y);
                rotatedImage.setPixel(y, x, originalPixel);
            }
        }
        processedImage = rotatedImage;
    }

    public void rotateRight() {
        APImage rotatedImage = new APImage(processedImage.getHeight(), processedImage.getWidth());
        for (int x = 0; x < processedImage.getWidth(); x++) {
            for (int y = 0; y < processedImage.getHeight(); y++) {
                Pixel originalPixel = processedImage.getPixel(x, y);
                rotatedImage.setPixel(rotatedImage.getWidth() - 1 - y, rotatedImage.getHeight() - 1 - x, originalPixel);
            }
        }
        processedImage = rotatedImage;
    }

    public void flip() {
        APImage flippedImage = new APImage(processedImage.getWidth(), processedImage.getHeight());
        for (int x = 0; x < processedImage.getWidth(); x++) {
            for (int y = 0; y < processedImage.getHeight(); y++) {
                Pixel orignalImagePixel = processedImage.getPixel(processedImage.getWidth() - 1 - x, processedImage.getHeight() - 1 - y);
                flippedImage.setPixel(x, y, orignalImagePixel);
            }
        }
        processedImage = flippedImage;
    }

    public void brightenImage(int brightenAmount) {
        for (Pixel pixel : processedImage) {
            int red = Math.min(pixel.getRed() + brightenAmount, 255);
            int green = Math.min(pixel.getGreen() + brightenAmount, 255);
            int blue = Math.min(pixel.getBlue() + brightenAmount, 255);
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
    }

    public void darkenImage(int darkenAmount) {
        for (Pixel pixel : processedImage) {
            int red = Math.max(0, pixel.getRed() - darkenAmount);
            int green = Math.max(0, pixel.getGreen() - darkenAmount);
            int blue = Math.max(0, pixel.getBlue() - darkenAmount);
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
    }

    public void applyColorFilter(int redFilter, int greenFilter, int blueFilter) {

        for (Pixel pixel : processedImage) {
            int red = Math.min(pixel.getRed() + redFilter, 255);
            int green = Math.min(pixel.getGreen() + greenFilter, 255);
            int blue = Math.min(pixel.getBlue() + blueFilter, 255);
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
        for (Pixel pixel : processedImage) {
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
        for (Pixel pixel : processedImage) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            pixel.setRed(255 - red);
            pixel.setGreen(255 - green);
            pixel.setBlue(255 - blue);
        }

    }

    public void sharpenImage(int degree, int threshold) {
        for (int y = 0; y < processedImage.getHeight() - 1; y++) {
            for (int x = 1; x < processedImage.getWidth(); x++) {
                Pixel cornerPixel = processedImage.getPixel(x - 1, y);
                Pixel rightPixel = processedImage.getPixel(x, y);
                Pixel bottomPixel = processedImage.getPixel(x, y + 1);

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

        for (int y = 1; y < processedImage.getHeight() - 2; y++) {
            for (int x = 1; x < processedImage.getWidth() - 2; x++) {
                Pixel topPixel = processedImage.getPixel(x, y - 1);
                Pixel bottomPixel = processedImage.getPixel(x, y + 1);
                Pixel rightPixel = processedImage.getPixel(x + 1, y);
                Pixel leftPixel = processedImage.getPixel(x - 1, y);

                int totalRed = topPixel.getRed() + bottomPixel.getRed() + rightPixel.getRed() + leftPixel.getRed();
                int totalGreen = topPixel.getGreen() + bottomPixel.getGreen() + rightPixel.getGreen() + leftPixel.getGreen();
                int totalBlue = topPixel.getBlue() + bottomPixel.getBlue() + rightPixel.getBlue() + leftPixel.getBlue();

                processedImage.getPixel(x, y).setRed(totalRed / 4);
                processedImage.getPixel(x, y).setGreen(totalGreen / 4);
                processedImage.getPixel(x, y).setBlue(totalBlue / 4);

            }
        }
    }

    public void shrinkImage(double shrinkFactor) {
        int newWidth = (int) (processedImage.getWidth() / shrinkFactor);
        int newHeight = (int) (processedImage.getHeight() / shrinkFactor);

        APImage shrunkImage = new APImage(newWidth, newHeight);

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;

                for (int dy = 0; dy < shrinkFactor; dy++) {
                    for (int dx = 0; dx < shrinkFactor; dx++) {
                        Pixel original = processedImage.getPixel((int) (x * shrinkFactor + dx), (int) (y * shrinkFactor + dy));
                        totalRed += original.getRed();
                        totalGreen += original.getGreen();
                        totalBlue += original.getBlue();
                    }
                }

                int shrunkRed = (int) (totalRed / (shrinkFactor * shrinkFactor));
                int shrunkGreen = (int) (totalGreen / (shrinkFactor * shrinkFactor));
                int shrunkBlue = (int) (totalBlue / (shrinkFactor * shrinkFactor));


                Pixel shrunkPixel = new Pixel(shrunkRed, shrunkGreen, shrunkBlue);
                shrunkImage.setPixel(x, y, shrunkPixel);
            }
        }
        processedImage = shrunkImage;
    }

    public void enlargenImage(double enlargenFactor) {
        int newWidth = (int) (processedImage.getWidth() * enlargenFactor);
        int newHeight = (int) (processedImage.getHeight() * enlargenFactor);

        APImage enlargedImage = new APImage(newWidth, newHeight);

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                enlargedImage.setPixel(x, y, processedImage.getPixel((int) (x / enlargenFactor), (int) (y / enlargenFactor)));
            }
        }

        processedImage = enlargedImage;
    }
}
