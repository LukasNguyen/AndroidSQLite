package com.example.student.model;

import java.io.Serializable;

/**
 * Created by Student on 10/18/2017.
 */

public class Book implements Serializable {
    private int id;
    private String name;
    private double price;


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book() {
    }

    public Book(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tên: " +this.name + "\n" + "Giá: "+this.price;
    }
}
