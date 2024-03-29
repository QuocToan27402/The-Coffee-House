package com.example.it_coffee.Model;

import static com.example.it_coffee.CartActivity.totalPriceView;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order implements Serializable {
    private String orderId, orderDate, customerId, address, comments;
    private double totalPrice;
    private List<Product> productList;

    public Order() {
        totalPrice = 0;
        productList = new ArrayList<>();
    }

    public Order(String orderId, String orderDate, String customerId, String address, String comments, double totalPrice, List<Product> productList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.address = address;
        this.comments = comments;
        this.totalPrice = totalPrice;
        this.productList = productList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getCart(){
        return productList;
    }
    public void setCart(List<Product> list){
        this.productList = list;
    }

    public void addProductToCart(Product product){
        totalPrice = totalPrice + product.getProductPrice() * product.quantityInCart;
        productList.add(product);
    }
    public void addTotal(Product product) {
        totalPrice = totalPrice + product.getProductPrice();
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);

        totalPriceView.setText(format.format(totalPrice));

//        totalPriceView.setText(new DecimalFormat("##.##").format(totalPrice));
    }
    public void minusTotal(Product product) {
        totalPrice = totalPrice - product.getProductPrice();
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);

        totalPriceView.setText(format.format(totalPrice));
    }
    public void removeProduct(Product product) {
        totalPrice = totalPrice - product.getProductPrice() * product.getQuantityInCart();
        productList.remove(product);
        if(totalPrice>0){
            Locale locale = new Locale("vi", "VN");
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            format.setRoundingMode(RoundingMode.HALF_UP);

            totalPriceView.setText(format.format(totalPrice));
        }
        else{
            Locale locale = new Locale("vi", "VN");
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            format.setRoundingMode(RoundingMode.HALF_UP);

            totalPriceView.setText(format.format(totalPrice));
        }

    }
}
