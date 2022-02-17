package com.solvd.seleniumgrid.web.service;

public enum ConfigData {

    BASE_URL("base_url"), BROWSER("browser"), EXPLICIT_TIMEOUT("explicit_timeout"),
    SELENIUM_URL("selenium_url"), VERSION("version");

    private final String title;

    ConfigData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
