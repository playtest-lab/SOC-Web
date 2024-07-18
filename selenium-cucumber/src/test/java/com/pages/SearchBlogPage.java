package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SearchBlogPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@data-cky-tag='accept-button']")
    WebElement cookies;

    @FindBy(xpath = "//div[@data-id='0d410f5']/div/form/div/input")
    WebElement inputSearch;

    @FindBy(xpath = "//div[@data-id='0d410f5']/div/form/div/button")
    WebElement searchButton;

    public SearchBlogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickButtonCookies() {
        wait.until(ExpectedConditions.visibilityOf(cookies));
        cookies.click();
    }

    public void inputSearch(String news) {
        wait.until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.sendKeys(news);
    }

    public void clickButtonSearch() {

        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }


}

