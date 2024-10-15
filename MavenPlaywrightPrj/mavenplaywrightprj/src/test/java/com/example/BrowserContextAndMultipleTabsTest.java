package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;

public class BrowserContextAndMultipleTabsTest {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions()
                .setHeadless(false)
                );

        BrowserContext context = browser.newContext();

        // Start tracing
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Checkboxes")).click();
        page.getByRole(AriaRole.CHECKBOX).first().check();
        page.getByRole(AriaRole.CHECKBOX).nth(1).uncheck();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("BrowserContextMultipleTabs1.png")));

        Page page2 = context.newPage();
        page2.navigate("https://the-internet.herokuapp.com");
        page2.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dropdown")).click();
        page2.getByRole(AriaRole.COMBOBOX).selectOption("Option 2");
        page2.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("BrowserContextMultipleTabs2.png")));

        page.getByRole(AriaRole.CHECKBOX).first().uncheck();
        page.getByRole(AriaRole.CHECKBOX).nth(1).check();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("BrowserContextMultipleTabs1a.png")));
        
        // Stop tracing and save the trace to a file
        context.tracing().stop(new Tracing.StopOptions().setPath(java.nio.file.Paths.get("trace.zip")));

        playwright.close();
    }
    
}
