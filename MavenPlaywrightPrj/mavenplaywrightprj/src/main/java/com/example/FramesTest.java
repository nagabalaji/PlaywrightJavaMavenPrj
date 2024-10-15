package com.example;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.regex.Pattern;


public class FramesTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            
            // Start tracing
            context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

            Page page = context.newPage();
            page.navigate("https://letcode.in/frame");

            Frame frame = page.frame("firstFr");
            frame.getByPlaceholder("Enter name").fill("My Name");
            frame.getByPlaceholder("Enter email").fill("My Email");

            page.pause();

            Frame innerFrame = page.frameByUrl(Pattern.compile("innerFrame"));
            innerFrame.getByPlaceholder("Enter email").fill("Inner Email");

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("validation_frame.png")));

            // Stop tracing and save the trace to a file
            context.tracing().stop(new Tracing.StopOptions().setPath(java.nio.file.Paths.get("trace.zip")));

            browser.close();
        }
    }
}
