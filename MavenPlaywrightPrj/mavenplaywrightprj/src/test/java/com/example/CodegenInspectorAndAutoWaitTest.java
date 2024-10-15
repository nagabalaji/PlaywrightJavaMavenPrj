package com.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import java.nio.file.Paths;

public class CodegenInspectorAndAutoWaitTest {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();

      // Start tracing
      context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

      Page page = context.newPage();
      page.navigate("https://letcode.in/edit");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).fill("crazy@gmail.com");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).press("Tab");
      page.getByPlaceholder("Enter password").fill("gmail12345");
      page.getByPlaceholder("Enter password").press("Enter");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign out")).click();
      page.getByLabel("Bye! See you soon :)").click();
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("codegenInspector.png")));

      // Stop tracing and save the trace to a file
      context.tracing().stop(new Tracing.StopOptions().setPath(java.nio.file.Paths.get("trace.zip")));

    }
  }
}