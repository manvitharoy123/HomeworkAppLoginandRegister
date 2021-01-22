package com.example.ns;

public class Upload {
    private String mImageUrl;
    private String mName;

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.mName = name;
        this.mImageUrl = imageUrl;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
