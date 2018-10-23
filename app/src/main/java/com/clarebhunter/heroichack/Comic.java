package com.clarebhunter.heroichack;

public class Comic {
    private String name;
    private String description;
    private String imagePath;

    public Comic(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public Comic(String name, String description) {
        this(name, description, "");
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.description;
    }
}
