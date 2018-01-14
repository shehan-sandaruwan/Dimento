package com.example.shehandinuka.dimento;

/**
 * Created by shehan dinuka on 09/01/2018.
 */

public class ImageDef {

    String imageUrl;
    String imageName;
    int numberOfView;

    public ImageDef(String imageUrl, String imageName, int numberOfView) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.numberOfView = numberOfView;
    }


    public String getNumberOfView() {
        return numberOfView+"";
    }

    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
