package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class LoginPage extends WebPage {

    public Locator emailFiled = $("input[placeholder=\"Email\"]"),
            passwordFiled = $("input[placeholder=\"Password\"]"),
            submitButton = $("button >> text=' Sign in '"),
            errorMessages = $(".error-texts li"),
            registerLink = $("text='Need an account?'");

    public LoginPage(String baseUrl, Page page){
        super(baseUrl, page);
    }

    public LoginPage open(){
        page.navigate(baseUrl + "/login", new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));
        return this;
    }

    public MainPage loginWith(String email, String password){
        emailFiled.fill(email);
        passwordFiled.fill(password);
        submitButton.click();
        return new MainPage(baseUrl, page);
    }

    public RegisterPage pressRegisterLink(){
        registerLink.click();
        return new RegisterPage(baseUrl, page);
    }


}
