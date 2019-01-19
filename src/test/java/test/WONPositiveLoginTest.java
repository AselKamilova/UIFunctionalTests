package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserUtils;

import java.util.concurrent.TimeUnit;

public class WONPositiveLoginTest {
    @Test
    public void positiveLoginTest(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/ login.aspx");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String actualTitle=driver.getTitle();
        String expected ="Web Order Login";

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);
        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        username.sendKeys("Tester");
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("test");
        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        BrowserUtils.verifyTextMatches(driver.getTitle(),"Web Order");
        Assert.assertEquals(driver.getTitle(),"Web Order");

        BrowserUtils.verifyTextMatches(driver.getCurrentUrl(),"http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/ login.aspx");
        Assert.assertEquals(driver.getCurrentUrl(),"http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/ login.aspx");
    }
}
