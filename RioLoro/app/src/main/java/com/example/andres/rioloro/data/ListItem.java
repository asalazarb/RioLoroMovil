package com.example.andres.rioloro.data;

/**
 * Created by usuario on 10/10/2017.
 */

public class ListItem {
    private String dateAndTime;
    private String message;
    private int colorResource;
    private String image;

    /*It's common for an "Item" to have a unique Id for storing an a Database
    private String uniqueIdentifier;
    */

    public ListItem(String dateAndTime, String message, int colorResource, String image) {
        this.dateAndTime = dateAndTime;
        this.message = message;
        this.colorResource = colorResource;
        this.image = image;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
