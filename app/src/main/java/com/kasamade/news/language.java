package com.kasamade.news;

/**
 * Created by Sachin Khairnar on 5/1/2017.
 */

public class language {

    public language(String code, String name, boolean selected) {
        super();
        this.code = code;
        this.name = name;
        this.selected = selected;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String code = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name = null;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    boolean selected = false;
}
