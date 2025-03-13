package com.pastebin.tests;

import org.openqa.selenium.WebElement;
import com.pastebin.pages.PastebinPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PastebinTest {
    private WebDriver driver;
    private PastebinPage pastebinPage;
    private final String PASTE_URL = "https://pastebin.com/";
    private final String PASTE_CODE = "git config --global user.name \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private final String PASTE_TITLE = "how to gain dominance among developers";

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://pastebin.com/");

        pastebinPage = new PastebinPage(driver);
    }



    @Test
    public void testCreatePaste() {
        pastebinPage.enterCode(PASTE_CODE);
        pastebinPage.selectSyntaxHighlighting();
        pastebinPage.selectPasteExpiration();
        pastebinPage.enterPasteTitle(PASTE_TITLE);
        pastebinPage.clickCreatePaste();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("Pastebin.com - #1 paste tool since 2002!")));

        String actualTitle = driver.getTitle();
        System.out.println("DEBUG: Actual page title: " + actualTitle);

        assertTrue(actualTitle.contains(PASTE_TITLE), "Page title does not match");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
