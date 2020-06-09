package io.github.fabiantauriello.check;

public class CustomSpinnerItem {
    private String text;
    private int image;

    public CustomSpinnerItem(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }
}
