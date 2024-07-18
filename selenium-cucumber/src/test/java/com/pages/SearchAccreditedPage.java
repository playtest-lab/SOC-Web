package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchAccreditedPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "ipt-busca-credenciado-2")
    WebElement inputSearch;

    @FindBy(id = "botao-buscar")
    WebElement searchButton;

    @FindBy(xpath = "//section[@data-codigo='6085']")
    WebElement accreditedButton;

    @FindBy(xpath = "//div[@class='infowindow-maps']/a")
    WebElement detailButton;

    public SearchAccreditedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void inputSearch(String accredited) {
        wait.until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.sendKeys(accredited);
    }

    public void clickButtonSearch() {

        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }

    public void clickButtonAccredited() {

        wait.until(ExpectedConditions.visibilityOf(accreditedButton));
        accreditedButton.click();
    }

    public void clickButtonDetail() {

        wait.until(ExpectedConditions.visibilityOf(detailButton));
        detailButton.click();
    }
}
