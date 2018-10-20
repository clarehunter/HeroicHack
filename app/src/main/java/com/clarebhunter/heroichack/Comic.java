package com.clarebhunter.heroichack;
import android.media.Image;

public class Comic {
    private String name;
    private String description;
    private Image thumbnail;

//    public Comic(String name, String description, Image thumbnail) {
//        this.name = name;
//        this.description = description;
//        this.thumbnail = thumbnail;
//    }
    public Comic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Image getThumbNail() {
        return this.thumbnail;
    }

    public String toString() {
        return this.name + ": " + this.description;
    }
}
