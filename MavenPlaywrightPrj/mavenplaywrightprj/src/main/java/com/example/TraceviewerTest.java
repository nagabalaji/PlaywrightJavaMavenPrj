package com.example;

import com.microsoft.playwright.*;
import java.nio.file.Paths;


public class TraceviewerTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setTimeout(30000));
            BrowserContext context = browser.newContext();
            
            // Start tracing
            context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

            Page page = context.newPage();
            page.navigate("https://letcode.in/edit");
            page.getByPlaceholder("Enter first & last name").click();
            page.getByPlaceholder("Enter first & last name").fill("this is my name changed");
            Locator locator = page.locator("#join");
            
            locator.click();
            locator.fill("HI");
            locator.press("End");
            locator.fill(" man");
            locator.press("Tab");
            
            String attribute = page.locator("id=getMe").getAttribute("value");
            System.out.println(attribute);
            
            page.locator("(//label[normalize-space(text())='Clear the text']/following::input)[1]").clear();
            
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("validation_passed.png")));

            // Stop tracing and save the trace to a file
            context.tracing().stop(new Tracing.StopOptions().setPath(java.nio.file.Paths.get("trace.zip")));

            browser.close();
        }
    }
}
