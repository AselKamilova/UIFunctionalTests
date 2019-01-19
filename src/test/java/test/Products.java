package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.TestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
       WO-1: Products1.
       Login to Web Ordersapplication using “Tester”and “test”
       2.Click on View all products link
       3.Remember all the product names from the table4.
       Click on View all orderslink1.
       Verify that all the values in the Productscolumn match the names from step 4.
        */
public class Products extends TestBase {
    @Test
    public void ViewAllProductLinks(){
        login();
        WebElement viewAllProducts=driver.findElement(By.xpath("//a[.='View all products']"));
        viewAllProducts.click();
      //  3.Remember all the product names from the table4.
        List<WebElement>productNames=driver.findElements(By.xpath("//table[@class='ProductsTable']/tbody/tr/td[1]"));

        for (WebElement products:productNames) {
            System.out.println("product names: " + products);
        }
        // Click on View all orderslink1.
        WebElement ordersLInk=driver.findElement(By.xpath("//a[.='View all orders']"));
        ordersLInk.click();

        //Verify that all the values in the Productscolumn match the names from step 4.
        List<WebElement>namesMatch=driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[3]"));

        for (WebElement names:namesMatch) {
            System.out.println(" names: "+names);

        }
        System.out.println(namesMatch.contains(productNames));
    }
    public void login(){
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);
    }
    /*
    WO-2: Create Order1.Login to Web Ordersapplication using “Tester”and “test”” 2.Click on Orderlink
    3.Select a product (Select different product every time)4.Enter data to all the required fields(Enter different data every time)
    5.Click Proceed 6.Click on link View all orders 7.Verify that all the order details are correct
     */
    @Test
    public void CreateOrder(){
        login();


    }
}
