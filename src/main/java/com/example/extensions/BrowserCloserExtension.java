package com.example.extensions;

import com.microsoft.playwright.Browser;
import org.junit.jupiter.api.extension.*;

public class BrowserCloserExtension implements AfterTestExecutionCallback {


    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Browser browser = context.getStore(ExtensionContext.Namespace.create("playwright.browser")).get("browser", Browser.class);
        if (browser != null){
            browser.close();
        }
    }


}
