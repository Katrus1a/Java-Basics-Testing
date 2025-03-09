package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PastebinPage {
    private WebDriver driver;

    private By newPasteTextArea = By.id("postform-text");
    private By pasteExpirationDropdown = By.id("postform-expiration");
    private By pasteTitleField = By.id("postform-name");
    private By createNewPasteButton = By.xpath("//button[text()='Create New Paste']");

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCode(String code) {
        driver.findElement(newPasteTextArea).sendKeys(code);
    }

    public void selectPasteExpiration(String value) {
        WebElement dropdown = driver.findElement(pasteExpirationDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public void enterPasteTitle(String title) {
        driver.findElement(pasteTitleField).sendKeys(title);
    }

    public void submitPaste() {
        driver.findElement(createNewPasteButton).click();
    }
}
