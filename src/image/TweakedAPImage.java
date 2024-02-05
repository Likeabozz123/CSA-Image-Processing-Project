package image;

import images.APImage;

public class TweakedAPImage extends APImage {

    public TweakedAPImage(int i, int i1) {
        super(i, i1);
    }

    public TweakedAPImage(String s) {
        super("src/resources/" + s);
    }

    public TweakedAPImage() {
    }
}
