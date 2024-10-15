package com.example;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import org.junit.*;

public class DropDownsTest {
  private Playwright playwright;
  private Browser browser;
  private Page page;

  @Before
  public void setUp() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    page = browser.newPage();
  }

  @Test
  public void test() {
    page.navigate("https://letcode.in/dropdowns");
    page.locator("#fruits").selectOption("0");
    page.getByText("You have selected Apple").click();
    page.locator("#superheros").selectOption("am");
    page.locator("#lang").selectOption("java");
    page.locator("#country").selectOption("India");
  }

  @After
  public void tearDown() {
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Dropdown_SS.png")));
    page.close();
    browser.close();
    playwright.close();
  }
}