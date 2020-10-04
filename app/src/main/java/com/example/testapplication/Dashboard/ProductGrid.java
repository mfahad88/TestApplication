
package com.example.testapplication.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductGrid {

    @SerializedName("grid_title")
    @Expose
    private String title;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("view_link_type")
    @Expose
    private String viewLinkType;
    @SerializedName("view_link")
    @Expose
    private String viewLink;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("error")
    @Expose
    private String error;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getViewLinkType() {
        return viewLinkType;
    }

    public void setViewLinkType(String viewLinkType) {
        this.viewLinkType = viewLinkType;
    }

    public String getViewLink() {
        return viewLink;
    }

    public void setViewLink(String viewLink) {
        this.viewLink = viewLink;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
