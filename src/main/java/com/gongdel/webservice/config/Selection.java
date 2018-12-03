package com.gongdel.webservice.config;

public enum  Selection {
    HOME("Home"),
    POST("Post"),
    CATEGORY("Category");

    private String value;

    Selection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
