package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class NewAngularJsApp {
    	public static void main(String[] args) {
		
	Playwright playwright = Playwright.create();
	Browser browser = playwright.webkit().launch(
			new BrowserType.LaunchOptions()
			.setHeadless(false)
			);
	Page page = browser.newPage();
	page.navigate("http://localhost:8080");
	String title = page.title();
	String URL = page.url();
	
	System.out.println("Page title: " + title );
	System.out.println("Page URL: " + URL);
	
	
	page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("IncrementCounter")).click();
    
	String counter = page.getByText("Counter:").innerText();
	System.out.println(counter);
	
//    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("DecrementCounter")).click();
//	counter = page.getByText("Counter:").innerText();
//	System.out.println(counter);
	


	page.close();
	browser.close();
	playwright.close();

	}
}
