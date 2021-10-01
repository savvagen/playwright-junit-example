package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class RegisterPage extends WebPage {

    public Locator loginLink = $("text='Have an account?'");

    public RegisterPage(String baseUrl, Page page) {
        super(baseUrl, page);
    }

    public RegisterPage open(){
        page.navigate(baseUrl + "/register", new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));
        return this;
    }
}
