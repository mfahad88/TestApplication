
package com.example.testapplication.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("final_price")
    @Expose
    private String finalPrice;
    @SerializedName("discount")
    @Expose
    private Boolean discount;
    @SerializedName("discount_percent")
    @Expose
    private Integer discountPercent;
    @SerializedName("karachi_only")
    @Expose
    private Boolean karachiOnly;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("reviews_summary")
    @Expose
    private String reviewsSummary;
    @SerializedName("reviews_count")
    @Expose
    private Object reviewsCount;
    private boolean isRating;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Boolean getKarachiOnly() {
        return karachiOnly;
    }

    public void setKarachiOnly(Boolean karachiOnly) {
        this.karachiOnly = karachiOnly;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReviewsSummary() {
        return reviewsSummary;
    }

    public void setReviewsSummary(String reviewsSummary) {
        this.reviewsSummary = reviewsSummary;
    }

    public Object getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Object reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public boolean isRating() {
        return isRating;
    }

    public void setRating(boolean rating) {
        isRating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", finalPrice='" + finalPrice + '\'' +
                ", discount=" + discount +
                ", discountPercent=" + discountPercent +
                ", karachiOnly=" + karachiOnly +
                ", image='" + image + '\'' +
                ", reviewsSummary='" + reviewsSummary + '\'' +
                ", reviewsCount=" + reviewsCount +
                ", isRating=" + isRating +
                '}';
    }
}
