package com.example.linhdq.taxi.model;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class ChildHelpModel {
    private byte index;
    private int image;
    private String title;
    private String content;

    public ChildHelpModel(byte index, int image, String title, String content) {
        this.index = index;
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public byte getIndex() {
        return index;
    }

    public void setIndex(byte index) {
        this.index = index;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
