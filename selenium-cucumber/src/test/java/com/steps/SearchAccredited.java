package com.steps;

import com.pages.SearchAccreditedPage;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class SearchAccredited {
    WebDriver driver;
    WebDriverWait wait;
    SearchAccreditedPage searchAccreditedPage;

    public SearchAccredited() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions optionsC = new ChromeOptions();
        optionsC.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors",
                "disable-popup-blocking", "disable-notifications", "no-sandbox"));
        driver = new ChromeDriver(optionsC);
        searchAccreditedPage = new SearchAccreditedPage(driver);
        driver.manage().window().maximize();
    }

    @Dado("que o usuario esteja no sistema da SOCNET")
    public void accessSystem() throws IOException {
        driver.get("https://socnet.soc.com.br/");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots\\Cenario 2\\1 - Dado que o usuario esteja no sistema da SOCNET.png"));
    }

    @Quando("realizar busca dos parceiro no cep {string}")
    public void searchAccredited(String news) throws IOException, InterruptedException {
        searchAccreditedPage.inputSearch(news);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots\\Cenario 2\\2 - Quando realizar a busca dos parceiro no cep 38400162.png"));
        searchAccreditedPage.clickButtonSearch();
    }

    @Entao("o sistema exibe a lista de credenciados daquela regiao")
    public void listAccredited() throws IOException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String title = driver.findElement((By.xpath("//p[@data-codigo='6085']/span"))).getText();
        Assert.assertEquals("UBERMED MEDICINA DO TRABALHO E SEGURANCA LTDA", title);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots\\Cenario 2\\3 - Entao o sistema exibe a lista de credenciados daquela regiao.png"));
    }

    @E("ao clicar na opcao de saiba mais")
    public void optionDetail() throws IOException {

        searchAccreditedPage.clickButtonAccredited();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots\\Cenario 2\\3.1 - E clica na opcao do credenciado desejado.png"));

        searchAccreditedPage.clickButtonDetail();
    }

    @Entao("o sistema direciona o usuario para a tela de detalhamento do credenciado")
    public void screenDetail() throws IOException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        assertTrue(driver.getCurrentUrl().contains("https://socnet.soc.com.br/perfil"));
        String nameCompany = driver.findElement((By.xpath("//div[@data-tooltip-bottom-left='UBERMED ']/span"))).getText();
        Assert.assertEquals("UBERMED", nameCompany);

        String nameCompanyComplete = driver.findElement((By.xpath("//div[@class='cnpj-empresa']"))).getText();
        Assert.assertEquals("09.001.688/0001-07", nameCompanyComplete);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots\\Cenario 2\\4 - Entao o sistema direciona o usuario para a tela de detalhamento do credenciado.png"));

        driver.quit();
    }
}
