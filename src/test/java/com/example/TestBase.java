package com.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {


    public static final String baseUrl = getProperty("BASE_URL", "http://localhost:4200");
    public static final String defaultEmail = "savva.genchevskiy@gmail.com", defaultPassword = "test.123";

    public static Playwright playwright;
    public static Browser browser;
    public BrowserContext context;
    public Page page;


    @BeforeAll
    public static void setUp(){
        System.out.println("The BASE_URL is: " + baseUrl);

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    }

    @BeforeEach
    public void setUpTest(){
        context = browser.newContext();
        context.clearCookies();
        page = context.newPage();
    }

    @AfterEach
    public void tearDownTest(){
        context.close();
    }

    @AfterAll
    public static void tearDown(){
        playwright.close();
    }



    public static String getProperty(String property, String defaultVal){
        return System.getenv(property) != null ? System.getenv(property) : defaultVal;
    }


}
