package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ParallelTests extends Thread {
    
    String browserName;

    ParallelTests(String browserName){
        this.browserName = browserName;
    }

    public void run() {
        System.out.println("Thread Running....");
        Playwright playwright = Playwright.create();
        Browser browser = getBrowser(playwright, browserName).launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate("https://letcode.in/edit");
        page.getByPlaceholder("Enter first & last name").click();
        page.getByPlaceholder("Enter first & last name").fill("this is my name changed");
        page.locator("#join").click();
        page.locator("#join").fill("HI");
        page.locator("#join").press("End");
        page.close();
        browser.close();
        playwright.close();
    }

    public static void main(String[] args) {
        Thread th = new ParallelTests("chrome");
        Thread th2 = new ParallelTests("chrome");
        Thread th3 = new ParallelTests("chrome");
        Thread th4 = new ParallelTests("chrome");
        th.start();
        th2.start();
        th3.start();
        th4.start();
    }

    public static BrowserType getBrowser(Playwright playwright, String browserName){
        switch(browserName){
            case "chrome":
                return playwright.chromium();
            case "firefox":
                return playwright.firefox();
            case "webkit":
                return playwright.webkit();
            default:
                throw new IllegalArgumentException("Invalid browser name");
        }
    }
}
