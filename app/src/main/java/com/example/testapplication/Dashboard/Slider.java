
package com.example.testapplication.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slider {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("link_type")
    @Expose
    private String linkType;
    @SerializedName("link")
    @Expose
    private String link;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Slider{" +
                "image='" + image + '\'' +
                ", linkType='" + linkType + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
