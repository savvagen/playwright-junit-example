package com.example.extensions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.*;
import java.lang.reflect.Parameter;

public class BrowserParameterResolver implements ParameterResolver {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface WebBrowser {
        String type() default "chromium";
        boolean headless() default true;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (parameterContext.isAnnotated(WebBrowser.class) &&
                parameterContext.getParameter().getType() == Browser.class);
    }

    @Override
    public Browser resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Browser browser = getBrowser(parameterContext.getParameter(), extensionContext);
        extensionContext.getStore(ExtensionContext.Namespace.create("playwright.browser")).put("browser", browser);
        return browser;
    }


    private Browser getBrowser(Parameter parameter, ExtensionContext context){
        Class<?> type = parameter.getType();
        if (Browser.class.equals(type)){

            String browserType = parameter.getDeclaredAnnotation(WebBrowser.class).type();
            boolean headlessMode = parameter.getDeclaredAnnotation(WebBrowser.class).headless();

            switch (browserType){
                case "chromium":
                    return Playwright.create().chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(headlessMode).setSlowMo(50));
                case "firefox":
                    return Playwright.create().firefox()
                            .launch(new BrowserType.LaunchOptions().setHeadless(headlessMode).setSlowMo(50));
                default:
                    throw new RuntimeException("\nInvalid Browser Type set for test: " + context.getRequiredTestMethod().getName()
                            + "\n" + "Expected: 'chromium' or 'firefox'. Found: '" + browserType + "'.");
            }

        }
        throw new ParameterResolutionException("Expecting type: Browser");

    }





}
