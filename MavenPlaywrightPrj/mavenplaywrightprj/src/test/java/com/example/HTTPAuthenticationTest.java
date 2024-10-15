package com.example;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;

public class HTTPAuthenticationTest {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions()
                .setHeadless(true)
                );

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));

        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/basic_auth");
        System.out.println(page.textContent("h3"));
        playwright.close();
    }
}
