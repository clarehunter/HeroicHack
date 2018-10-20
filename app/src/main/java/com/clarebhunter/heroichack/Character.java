package com.clarebhunter.heroichack;

public class Character {

    private String name;
    private boolean isSelected = false;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
