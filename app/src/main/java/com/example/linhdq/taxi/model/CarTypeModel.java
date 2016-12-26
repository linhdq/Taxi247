package com.example.linhdq.taxi.model;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class CarTypeModel {
    private int image;
    private String type;

    public CarTypeModel(int image, String type) {
        this.image = image;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
