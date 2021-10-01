package com.example;

import com.example.extensions.BrowserCloserExtension;
import com.example.extensions.BrowserParameterResolver;
import com.example.pages.LoginPage;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import com.example.extensions.BrowserParameterResolver.WebBrowser;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Tag("login")
@ExtendWith({BrowserParameterResolver.class, BrowserCloserExtension.class})
public class LoginTest extends TestBase {

    /*
    @Test
    public void shouldLoginToSystem(){
        page.navigate("http://localhost:4200/login");
        page.fill("input[placeholder=\"Email\"]", defaultEmail);
        page.fill("input[placeholder=\"Password\"]", defaultPassword);
        //page.keyboard().press("Enter");
        page.isEnabled("button >> text=' Sign in '");
        page.click("button >> text=' Sign in '");

        assertTrue(page.locator("a >> text=\"savva.genchevskiy\"").isVisible(new Locator.IsVisibleOptions().setTimeout(5000)));
        page.screenshot(new Page.ScreenshotOptions().setFullPage(true)
                .setPath(Paths.get("src/test/java/com/example/login-test.png"))
                .setType(ScreenshotType.PNG));
    }
    */

    @Test
    @Tag("positive")
    public void shouldLogin(){
        assertTrue(()-> new LoginPage(baseUrl, page)
                .open()
                .loginWith(defaultEmail, defaultPassword)
                .accountButton.isVisible());
    }

    @Test
    @Tag("positive")
    public void shouldLoginWithCustomBrowser(@WebBrowser(headless = false) Browser browser){
        var mainPage = new LoginPage(baseUrl, browser.newContext().newPage())
                .open()
                .loginWith(defaultEmail, defaultPassword);
        assertTrue(mainPage.accountButton.isVisible());
    }

    @Test
    public void shouldRedirectToRegisterPage(){
        var registerPage = new LoginPage(baseUrl, page)
                .open()
                .pressRegisterLink();
        assertTrue(registerPage.loginLink.isVisible());
        assertTrue(page.url().contains("register"));
        assertEquals(page.title(), "Conduit");
    }


    @Test
    public void shouldLoginWithInvalidCredentials(){
        page.navigate("http://localhost:4200/login");
        page.fill("input[placeholder=\"Email\"]", "test");
        page.fill("input[placeholder=\"Password\"]", "test");
        page.click("button >> text=' Sign in '");
        System.out.println(Arrays.toString(page.locator(".error-messages li").allInnerTexts().toArray()));
    }





}
