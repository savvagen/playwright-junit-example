package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class WebPage {

    public String baseUrl;
    public Page page;

    public WebPage(String baseUrl, Page page){
        this.page= page;
        this.baseUrl = baseUrl;
    }

    public Locator $(String selector){
        return this.page.locator(selector);
    }

}
