package com.example.linhdq.taxi.model;

/**
 * Created by LinhDQ on 12/20/16.
 */

public class ObjectDrawerItem {
    private int icon;
    private String name;
    private boolean isSelected;

    public ObjectDrawerItem(int icon, String name, boolean isSelected) {
        this.icon = icon;
        this.name = name;
        this.isSelected = isSelected;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
