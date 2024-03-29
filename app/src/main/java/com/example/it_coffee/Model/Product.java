package com.example.it_coffee.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private String productId;
    private String productName;
    int quantityInCart;

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    public Product(String productId, String productName, double productPrice, String productImageUrl, String categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Product() {
    }

    private double productPrice;
    private String productImageUrl;
    private String categoryId;

//    public String productName;
//    public String productPrice;
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public Product() {
//    }
//
//    public String getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(String productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public Product(String productName, String productPrice, String productImage) {
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productImage = productImage;
//    }
//
//    public String getProductImage() {
//        return productImage;
//    }
//
//    public void setProductImage(String productImage) {
//        this.productImage = productImage;
//    }
//
//    public String productImage;
////    public String productDestription;
//
//    public static List<Product> getAll(){
//        List<Product> list = new ArrayList<>();
//        list.add(new Product("Cà phê sữa", "18.000 đ", "capheden"));
//        list.add(new Product("Cà phê sữa", "18.000 đ", "capheden"));
//        list.add(new Product("Cà phê sữa", "18.000 đ", "capheden"));
//        list.add(new Product("Cà phê sữa", "18.000 đ", "capheden"));
//        list.add(new Product("Cà phê sữa", "18.000 đ", "capheden"));
//        list.add(new Product("Cà phê sữa", "18.000 đ", "capheden"));
//        return list;
//    }
}
