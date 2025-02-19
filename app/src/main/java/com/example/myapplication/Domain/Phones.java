package com.example.myapplication.Domain;

import java.io.Serializable;

public class Phones implements Serializable {
    private boolean BestPhone;
    private int CategoryId;
    private String Description;
    private  int Id;
    private String ImagePath;
    private int LocationId;
    private int Price;
    private int PriceId;
    private double Star;
    private int TimeId;
    private int TimeValue;
    private String Title;
    private int numberInCart;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Phones() {
    }

    @Override
    public String toString() {
        return  Title;
    }
    public boolean isBestPhone() {
        return BestPhone;
    }

    public void setBestPhone(boolean bestPhone) {
        BestPhone = bestPhone;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        LocationId = locationId;
    }

    public int getPrice() {return Price;}

    public void setPrice(int price) {
        Price = price;
    }

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        PriceId = priceId;
    }

    public double getStar() {return Star;}

    public void setStar(double star) {
        Star = star;
    }

    public int getTimeId() {
        return TimeId;
    }

    public void setTimeId(int timeId) {
        TimeId = timeId;
    }

    public int getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(int timeValue) {
        TimeValue = timeValue;
    }

    public String getTitle() {return Title;}

    public void setTitle(String title) {Title = title;}

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

}
