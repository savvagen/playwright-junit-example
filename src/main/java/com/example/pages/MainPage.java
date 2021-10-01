package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage extends WebPage{

    public Locator accountButton = $("a >> text=\"savva.genchevskiy\"");

    public MainPage(String baseUrl, Page page) {
        super(baseUrl, page);
    }

}
