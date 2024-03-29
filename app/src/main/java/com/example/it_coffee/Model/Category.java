package com.example.it_coffee.Model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryId;
    private String categoryName;

    public Category() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public Category(String categoryId, String categoryName, String categoryImageUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
    }

    private String categoryImageUrl;


//    public Category(String categoryName, String categoryImage) {
//        this.categoryName = categoryName;
//        this.categoryImage = categoryImage;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public String getCategoryImage() {
//        return categoryImage;
//    }
//
//    public void setCategoryImage(String categoryImage) {
//        this.categoryImage = categoryImage;
//    }
//
//    public String categoryName;
//
//    public Category() {
//    }
//
//    public String categoryImage;
//    public static List<Category> getAll(){
//        List<Category> list = new ArrayList<>();
//        list.add(new Category("Coffee", "icon_cate_coffee"));
//        list.add(new Category("Milk Tea", "icon_cate_milktea"));
//        list.add(new Category("Cake", "icon_cate_cake"));
//        return list;
//    }


}
