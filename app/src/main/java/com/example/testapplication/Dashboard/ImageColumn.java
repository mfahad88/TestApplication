package com.example.testapplication.Dashboard;

public class ImageColumn {
    private String columns;
    private Image image;


    public ImageColumn(String columns, Image image) {
        this.columns = columns;
        this.image = image;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageColumn{" +
                "columns='" + columns + '\'' +
                ", image=" + image +
                '}';
    }
}
