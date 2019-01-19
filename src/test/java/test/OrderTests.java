package test;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AllOrdersPage;
import pages.AllProductsPage;
import pages.LoginPage;
import pages.OrderPage;
import utilities.BrowserUtils;
import utilities.TestBase;

import java.util.List;

import static org.testng.Assert.*;
public class OrderTests extends TestBase {

    LoginPage loginPage;
    AllOrdersPage allOrdersPage;
    AllProductsPage allProductsPage;
    OrderPage orderPage;
    Faker faker;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage();
        allOrdersPage = new AllOrdersPage();
        allProductsPage = new AllProductsPage();
        orderPage = new OrderPage();

        loginPage.login(driver);
    }
    @AfterMethod
    public void tearDownPage() {
        loginPage = null;
        allOrdersPage = null;
        allProductsPage = null;
        orderPage = null;
    }

    /*
    WO-2: Create Order
    1.Login to Web Orders application using “Tester” and “test”
    2.Click on Order link
    3.Select a product (Select different product every time)
    4.Enter data to all the required fields (Enter different data every time)
    5.Click Proceed
    6.Click on link View all orders
    7.Verify that all the order details are correct
     */
    @Test
    public void createOrder() {
        // 1. Login to Web Orders application using "Tester" and "test"
        /* action performed by calling .login() method within @BeforeMethod */

        // 2. Click on Order link
        allOrdersPage.goToOrderPage.click();

        // 3. Select a product (Select different product every time)
        int randomProductIndex = (int) (Math.random() * 3);
        Select productSelection = orderPage.getProductList();
        productSelection.selectByIndex(randomProductIndex);
        String orderedProduct = productSelection.getFirstSelectedOption().getText();

        // 4. Enter data to all the required fields (Enter different data every time)
        faker = new Faker();

        String orderedQuantity = "" + faker.number().numberBetween(1, 10);
        orderPage.qtyInput.sendKeys(orderedQuantity);

        String orderedCustomerName = faker.name().fullName().trim();
        orderPage.customerNameInput.sendKeys(orderedCustomerName);

        String orderedStreetAddress = faker.address().streetAddress().trim();
        orderPage.streetAddressInput.sendKeys(orderedStreetAddress);

        String orderedCity = faker.address().city().trim();
        orderPage.cityInput.sendKeys(orderedCity);

        String orderedZip = faker.address().zipCode().substring(0, 5);
        orderPage.zipInput.sendKeys(orderedZip);

        orderPage.selectCardByIndex(faker.number().numberBetween(1, 3));
        String orderedCardType = orderPage.getSelectedCardName(driver);

        String orderedCardNumber = faker.number().digits(16);
        orderPage.cardNumberInput.sendKeys(orderedCardNumber);

        String orderedExpirationDate = BrowserUtils.generateDateMM$YY();
        orderPage.expirationDateInput.sendKeys(orderedExpirationDate);

        // 5. Click Proceed
        orderPage.processButton.click();

        // 6. Click on link View all orders
        orderPage.goToAllOrdersPage.click();

        // 7. Verify that all the order details are correct
        List<WebElement> rowElements = driver.findElements(By
                .xpath(allOrdersPage.getTableElementXPath(1)));
        String orderContent = "";
        for (WebElement each : rowElements)
            orderContent += each.getText() + " / ";

        assertTrue(orderContent.contains(orderedProduct));
        assertTrue(orderContent.contains(orderedQuantity));
        assertTrue(orderContent.contains(orderedCustomerName));
        assertTrue(orderContent.contains(orderedStreetAddress));
        assertTrue(orderContent.contains(orderedCity));
        assertTrue(orderContent.contains(orderedZip));
        assertTrue(orderContent.contains(orderedCardType));
        assertTrue(orderContent.contains(orderedCardNumber));
        assertTrue(orderContent.contains(orderedExpirationDate));
    }
}
