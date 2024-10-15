package com.example;

import org.junit.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Unit test for simple App.
 */
public class SimpleTest 
{

    @Test
	public void testMethod1() {
		
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                .setHeadless(false)
                );
        Page page = browser.newPage();
        page.navigate("https://www.google.com");
        String title = page.title();
        String URL = page.url();
        
        System.out.println("Page title: " + title );
        System.out.println("Page URL: " + URL);
        
        page.close();
        browser.close();
        playwright.close();
        }
}
