package com.epam.charsequence.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CharacterPage {
    private WebDriver driver;

    public CharacterPage(WebDriver driver) {
        this.driver = driver;
    }

    private By inputField = By.id("input-text");
    private By resultField = By.id("result-unequal");
    private By submitButton = By.id("submit");

    public void enterText(String text) {
        driver.findElement(inputField).sendKeys(text);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getResult() {
        return driver.findElement(resultField).getText();
    }
}
