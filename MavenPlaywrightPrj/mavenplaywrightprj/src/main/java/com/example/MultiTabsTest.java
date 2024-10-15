package com.example;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.List;

public class MultiTabsTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            
            // Start tracing
            context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

            //Single Page window handling
            Page page = context.newPage();
            page.navigate("https://letcode.in/windows");

            page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(
                p->p.context().pages().size() == 3), 
                () -> page.locator("id=multi").click());
                List<Page> pages = page.context().pages();
                for (Page tabs : pages) {
                    System.out.println(tabs.url());
                }

                Page alertPage = pages.get(1);
                Page dropdownPage = pages.get(2);

                System.out.println("Alert Page URL: " + alertPage.textContent("h1"));
                System.out.println("Dropdown Page URL: " + dropdownPage.textContent("h1"));

                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("validation_windows.png")));

                // Stop tracing and save the trace to a file
                context.tracing().stop(new Tracing.StopOptions().setPath(java.nio.file.Paths.get("trace.zip")));

                playwright.close();
            }
        }
}
