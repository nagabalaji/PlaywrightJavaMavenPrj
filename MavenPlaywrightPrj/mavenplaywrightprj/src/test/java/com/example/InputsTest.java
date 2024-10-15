package com.example;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class InputsTest {

    	public static void main(String[] args) {
		
	Playwright playwright = Playwright.create();
	Browser browser = playwright.chromium().launch(
			new BrowserType.LaunchOptions()
			.setHeadless(false)
//			.setChannel("chrome")
			);
	Page page = browser.newPage();
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
	
	page.close();
	browser.close();
	playwright.close();
	}

    
}
