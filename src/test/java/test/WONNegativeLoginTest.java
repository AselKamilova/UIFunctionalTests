package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;

import static java.lang.Thread.sleep;

public class WONNegativeLoginTest {
    WebDriver driver;
@BeforeMethod
    public void setUp(){
    WebDriverManager.chromedriver().setup();
     driver=new ChromeDriver();

}
    @Test
    public void negativeLoginTestWrongUsername() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        String actualTitle = driver.getTitle();
        String expected = "Web Orders Login";

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        String currentUrl = driver.getCurrentUrl();

        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        username.sendKeys("Test");

        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("Test");

        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        BrowserUtils.verifyTextMatches(driver.getCurrentUrl(), currentUrl);
        Assert.assertEquals(driver.getCurrentUrl(), currentUrl);
    }

    @Test
    public void negativeLoginTestWrongPassword () {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        String actualTitle = driver.getTitle();
        String expected = "Web Orders Login";

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        String currentUrl = driver.getCurrentUrl();

        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        username.sendKeys("Tester");

        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("Tester");

        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        BrowserUtils.verifyTextMatches(driver.getCurrentUrl(), currentUrl);
        Assert.assertEquals(driver.getCurrentUrl(), currentUrl);
    }

    @Test
    public void negativeLoginTestBlankUsername() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        String actualTitle = driver.getTitle();
        String expected = "Web Orders Login";

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        String currentUrl = driver.getCurrentUrl();

        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("test");

        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        BrowserUtils.verifyTextMatches(driver.getCurrentUrl(), currentUrl);
        Assert.assertEquals(driver.getCurrentUrl(), currentUrl);
    }

    @Test
    public void negativeLoginTestBlankPassword() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        String actualTitle = driver.getTitle();
        String expected = "Web Orders Login";

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        String currentUrl = driver.getCurrentUrl();

        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        username.sendKeys("Tester");

        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        BrowserUtils.verifyTextMatches(actualTitle, expected);
        Assert.assertEquals(actualTitle, expected);

        BrowserUtils.verifyTextMatches(driver.getCurrentUrl(), currentUrl);
        Assert.assertEquals(driver.getCurrentUrl(), currentUrl);
    }
@AfterMethod
    public void tearDown() throws InterruptedException {
   Thread.sleep(3000);
   driver.quit();

}
}
