
package com.example.testapplication.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Row {

    @SerializedName("columns")
    @Expose
    private String columns;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Row{" +
                "columns='" + columns + '\'' +
                ", images=" + images +
                '}';
    }
}
