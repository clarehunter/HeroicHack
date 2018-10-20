package com.clarebhunter.heroichack;

public class Character {

    private String name;
    private int id;
    private boolean isSelected = false;

    public Character(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
