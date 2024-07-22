package com.steps;

import com.pages.SearchBlogPage;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    WebDriver driver;
    SearchBlogPage searchBlogPage;

    public SearchSteps() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions optionsC = new ChromeOptions();
        optionsC.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors",
                "disable-popup-blocking", "disable-notifications", "no-sandbox"));
        driver = new ChromeDriver(optionsC);
        searchBlogPage = new SearchBlogPage(driver);
        driver.manage().window().maximize();
    }

    @Dado("que o usuario esteja no sistema da SOC")
    public void  accessSystem() throws IOException {
        driver.get("https://ww2.soc.com.br/blog");
        searchBlogPage.clickButtonCookies();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/Cenario 1/1 - Dado que o usuario esteja no sistema da SOC.png"));
    }

    @Quando("realizar busca da noticia {string}")
    public void searchNew(String news) throws IOException {
        searchBlogPage.inputSearch(news);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/Cenario 1/2 - Quando realizar busca da noticia NR-26.png"));
        searchBlogPage.clickButtonSearch();
    }

    @Entao("o sistema direciona o usuario para a tela da noticia pesquisada")
    public void  screenNew() throws IOException {
        assertTrue(driver.getCurrentUrl().contains("https://www.soc.com.br/?s=NR-26"));
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/Cenario 1/3 - Entao o sistema direciona o usuario para a tela da noticia pesquisada.png"));
    }

    @E("exibe o titulo com a chave buscada")
    public void titleNew() throws IOException {
        String title = driver.findElement((By.xpath("//div[@data-id='a57d20c']"))).getText();
        Assert.assertEquals("Resultados da Pesquisa por: NR-26", title);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/Cenario 1/4 - E exibe o titulo com a chave buscada.png"));
        driver.quit();
    }

}


