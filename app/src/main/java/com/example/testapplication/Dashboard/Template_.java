
package com.example.testapplication.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Template_ {

    @SerializedName("row_type")
    @Expose
    private String rowType;
    @SerializedName("row_value")
    @Expose
    private String rowValue;

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public String getRowValue() {
        return rowValue;
    }

    public void setRowValue(String rowValue) {
        this.rowValue = rowValue;
    }

    @Override
    public String toString() {
        return "Template_{" +
                "rowType='" + rowType + '\'' +
                ", rowValue='" + rowValue + '\'' +
                '}';
    }
}
