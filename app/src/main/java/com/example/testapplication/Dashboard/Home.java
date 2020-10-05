package com.example.testapplication.Dashboard;

public class Home {
    public static int VIEW_TITLE=0;
    public static int VIEW_IMAGE=1;
    private int viewType;
    private String title;
    private Image image;
    private int columns;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
