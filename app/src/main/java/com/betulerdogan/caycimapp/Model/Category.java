package com.betulerdogan.caycimapp.Model;

public class Category {

    private String Name;
    private String Image;
    private String Price;



    public Category() {
    }

    public Category(String name, String image, String Price) {
        Name = name;
        Image = image;
        this.Price = Price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
}
