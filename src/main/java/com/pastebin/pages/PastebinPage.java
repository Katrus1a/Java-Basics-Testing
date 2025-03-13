package com.pastebin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By codeInput = By.id("postform-text");
    private By syntaxDropdown = By.id("select2-postform-format-container");
    private By bashOption = By.xpath("//li[text()='Bash']");
    private By expirationDropdown = By.id("select2-postform-expiration-container");
    private By expiration10Min = By.xpath("//li[text()='10 Minutes']");
    private By titleInput = By.id("postform-name");
    private By createPasteButton = By.xpath("//button[text()='Create New Paste']");

    public void enterCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(codeInput)).sendKeys(code);
    }

    public void selectSyntaxHighlighting() {
        wait.until(ExpectedConditions.elementToBeClickable(syntaxDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(bashOption)).click();
    }

    public void selectPasteExpiration() {
        wait.until(ExpectedConditions.elementToBeClickable(expirationDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(expiration10Min)).click();
    }

    public void enterPasteTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).sendKeys(title);
    }

    public void clickCreatePaste() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createPasteButton));
        button.click();
    }

}
