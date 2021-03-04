package org.startupranking.srchallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppRater {

    private static WebDriver driver;

    public static void launchBrowser(){
        String baseUrl = "https://apprater.net/add/";
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") +  "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

    }

    public static void send(String selectorType, String selector, String value){
        switch (selectorType) {
            case "css":
                driver.findElement(By.cssSelector(selector)).sendKeys(value);
                break;
            case "name":
                driver.findElement(By.name(selector)).sendKeys(value);
                break;
            case "id":
                driver.findElement(By.id(selector)).sendKeys(value);
                break;
            case "xpath":
                driver.findElement(By.xpath(selector)).sendKeys(value);
                break;
        }
    }

    public static void sendText(String label, String selectorType, String selector, String value){
        try {
            Thread.sleep(1500);
            driver.findElements(By.tagName("fieldset")).clear();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        List<WebElement> list =  driver.findElements(By.tagName("label"));
        list.add(driver.findElement(By.tagName("textArea")));

        for (WebElement webE : list) {
            if (label.contentEquals(webE.getText())) {
                send(selectorType, selector, value);
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        launchBrowser();

        sendText("Your Name", "name", "user-submitted-name", "Lucas Ocampo");
        sendText("Email Address", "css", "#usp_form > div:nth-child(3) > div:nth-child(2) > fieldset > input", "Lucas@Ocampo.com");
        sendText("Product Title", "css", "#usp_form > fieldset.usp-title > input", "Prisma - AI that runs your phots into artwork in seconds");
        sendText("Product URL", "xpath", "//*[@id=\"usp_form\"]/div[4]/fieldset/input", "https://apprater.net/add/Prisma");
        sendText("Product Tags", "xpath", "//*[@id=\"usp_form\"]/div[5]/div[1]/fieldset/input", "game, Android, IA");


    }
}
