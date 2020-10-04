
package com.example.testapplication.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Template {

    @SerializedName("sliders")
    @Expose
    private List<Slider> sliders = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("template")
    @Expose
    private List<Template_> template = null;

    public List<Slider> getSliders() {
        return sliders;
    }

    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Template_> getTemplate() {
        return template;
    }

    public void setTemplate(List<Template_> template) {
        this.template = template;
    }

}
