package com.epam.charsequence.tests;

import com.epam.charsequence.pages.CharacterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
    private WebDriver driver;
    private CharacterPage characterPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/index.html");

        try {
            Thread.sleep(5000); // Додає паузу в 5 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        characterPage = new CharacterPage(driver);
    }


    @Test
    public void testMaxUnequalCharacters() {
        characterPage.enterText("aaabbccddeeffgg");
        characterPage.clickSubmit();
        assertEquals("2", characterPage.getResult(), "Incorrect result for unequal characters!");
    }

    @Test
    public void testMaxConsecutiveLetters() {
        characterPage.enterText("aaabbbbccddeee");
        characterPage.clickSubmit();
        assertEquals("4", characterPage.getResult(), "Incorrect result for consecutive letters!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
