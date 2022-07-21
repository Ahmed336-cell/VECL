package com.vecl.vecl.model;

public class NewCarModel {
    private String img_url;
    private int num_models;
    private int max_car_id;
    private int id;
    private String name;
    private double avg_horsepower;
    private double avg_price;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getNum_models() {
        return num_models;
    }

    public void setNum_models(int num_models) {
        this.num_models = num_models;
    }

    public int getMax_car_id() {
        return max_car_id;
    }

    public void setMax_car_id(int max_car_id) {
        this.max_car_id = max_car_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvg_horsepower() {
        return avg_horsepower;
    }

    public void setAvg_horsepower(double avg_horsepower) {
        this.avg_horsepower = avg_horsepower;
    }

    public double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(double avg_price) {
        this.avg_price = avg_price;
    }
}
