package com.example.linhdq.taxi.model;

/**
 * Created by LinhDQ on 12/20/16.
 */

public class ObjectDrawerItem {
    private int icon;
    private String name;

    public ObjectDrawerItem(int icon, String name) {
        this.icon = icon;
        this.name = name;
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
}
