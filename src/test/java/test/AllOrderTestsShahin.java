package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AllOrdersPage;
import pages.AllProductsPage;
import pages.LoginPage;
import pages.OrderPage;

import utilities.BrowserUtils;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static pages.AllOrdersPage.getEditButtonXPath;

public class AllOrderTestsShahin extends TestBase {
    LoginPage loginPage;
    AllOrdersPage allOrdersPage;
    AllProductsPage allProductsPage;
    OrderPage orderPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage();
        allOrdersPage = new AllOrdersPage();
        orderPage = new OrderPage();
        loginPage.login(driver);
    }
    @AfterMethod
    public void tearDownPage() {
        loginPage = null;
        allOrdersPage = null;
        orderPage = null;
    }

    /**
     * WO-1: Products
     * 1.Login to Web Orders application using “Tester” and “test”
     * 2.Click on View all products link
     * 3.Remember all the product names from the table
     * 4.Click on View all orders link
     * 5.Verify that all the values in the Products column match the names from step 4.
     */
    @Test (priority = 1)
    public void productsNamesVerification() {
        // 1. Login to Web Orders application using "Tester" and "test"
        /* takes place within @BeforeMethod by calling positive .login() method */

        // 2. Click on View all products link
        allOrdersPage.goToProductsPage.click();

        // 3. Remember all the product names from the table
        /* save all WebElements under 'Product name' into a list */
        List<WebElement> productElements = driver.findElements(By
                .xpath(AllProductsPage.getTableElementXPath("Product name")));
        List<String> productNames = new ArrayList<>();
        /* save the webelement text within the String ArrayList */
        for (WebElement each : productElements)
            productNames.add(each.getText());

        // 4. Click on View all orders link
        allProductsPage = new AllProductsPage();
        allProductsPage.goToAllOrdersPage.click();

        // 5. Verify that all the values in the Products column match the names from step 4.
        /* save all WebElements under "Product" into a list */
        List<WebElement> orderProductElements = driver.findElements(By
                .xpath(AllOrdersPage.getTableElementXPath("Product")));
        List<String> orderProductNames = new ArrayList<>();
        /* save the WebElement text within the String ArrayList */
        for (WebElement each : orderProductElements)
            orderProductNames.add(each.getText());

        for (String each : orderProductNames)
            assertTrue(productNames.contains(each));
    }

    /**
     * WO-3: Delete
     * 1. Login to Web Orders application using “Tester” and “test”
     * 2. Delete a random entry from the table
     * 3. Verify that table row count decreased by 1
     * 4. Verify Name column does not contain deleted person’s name
     */
    @Test (priority = 2)
    public void tableElementDeletion() {
        // 1. Login to Web Orders application using "Tester" and "test"
        /* This task is taken care of by positive login() method withi @BeforeMethod */

        // 2. Delete a random entry from the table
        // a. find the number of rows in the table
        List<WebElement> rows = driver.findElements(By.xpath(allOrdersPage.getTableElementXPath("Name")));
        int numberOfRowsInitial = rows.size();
        // b. generate a random number within the boundaries of the numberOfRowsInitial
        int random = (int) (1 + Math.random() * numberOfRowsInitial);
        // c. store the name of the person within the row that is to be deleted
        String nameDeleted = driver.findElement(By
                .xpath(allOrdersPage.getTableElementXPath("Name", random)))
                .getText();
        // d. check the checkbox of the row
        driver.findElement(By.xpath(allOrdersPage.getCheckboxXPath(random))).click();
        // e. click the Delete Selected
        allOrdersPage.deleteSelectedButton.click();

        // 3. Verify that table row count decreased by 1
        // a. find the number of rows in the new table
        rows = driver.findElements(By.xpath(allOrdersPage.getTableElementXPath("Name")));
        int numberOfRowsAfter = rows.size();
        // b. verify the size difference
        assertEquals(numberOfRowsAfter, numberOfRowsInitial - 1,
                "Expected number of rows after deleting 1 row: " + (numberOfRowsInitial-1));

        // 4. Verify Name column does not contain deleted person’s name
        // a. get all names from within the Name column
        String namesRemaining = BrowserUtils.toStringWebElements(By.xpath(allOrdersPage.getTableElementXPath("Name")));
        // b. verify that the deleted name is not within the remaining names
        assertFalse(namesRemaining.contains(nameDeleted),
                nameDeleted + " should not appear within the names remaining on the table");

    }

    /**
     * WO-4: Edit
     * 1. Login to Web Orders application using “Tester” and “test”
     * 2. Click edit button for any entry
     * 3. Change the quantity to a different amount
     * 4. Click Calculate
     * 5. Verify that new quantity is displayed
     * 6. Click Update
     * 7. Verify new quantity is displayed
     * 8. Verify that other information in that row did not change
     */
    @Test (priority = 3)
    public void quantityEdition() {
        // 1. Login to Web Orders application using "Tester" and "test"
        /* This task is taken care of by positive login() method within @BeforeMethod */

        // 2. Click edit button for any entry
        // a. generate a random index to be clicked on
        int randomRowIndex = (int) (1 + Math.random() * 8);
        // b. store the information within the selected row into a String to verify later
        String rowElementsText = BrowserUtils.toStringWebElements(
                By.xpath(allOrdersPage.getTableElementXPath(randomRowIndex)));
        // c. click on the random edit button
        driver.findElement(By.xpath(getEditButtonXPath(randomRowIndex))).click();

        // 3. Change the quantity to a different amount
        // a. generate a randomRowIndex quantity to edit
        int randomQty = (int) (Math.random() * 100);
        // b. store the previous quantity
        int originalQty = Integer.parseInt(orderPage.qtyInput.getAttribute("value"));
        // c. change the quantity to the randomQty
        orderPage.qtyInput.clear();
        orderPage.qtyInput.sendKeys("" + randomQty);

        // 4. Click Calculate
        orderPage.calculateButton.click();

        // 5. Verify that new quantity is displayed
        int displayedQty = Integer.parseInt(orderPage.qtyInput.getAttribute("value"));
        assertEquals(randomQty, displayedQty, "Quantity displayed should be " + randomQty);

        // 6. Click Update
        orderPage.updateButton.click();

        // 7. Verify new quantity is displayed
        displayedQty = Integer.parseInt(driver.findElement(By.xpath(
                allOrdersPage.getTableElementXPath("#", randomRowIndex)))
                .getText());
        assertEquals(displayedQty, randomQty, "Displayed quantity should be " + randomQty);

        // 8. Verify that other information in that row did not change
        // a. update the original rowElementsText string with new quantity
        rowElementsText = rowElementsText.replace(" " + originalQty + " ", " " + displayedQty + " ");
        // b. get the new text of the whole row
        String updatedRowElementsText = BrowserUtils.toStringWebElements(
                By.xpath(allOrdersPage.getTableElementXPath(randomRowIndex)));
        // c. compare the two String texts
        assertEquals(updatedRowElementsText, rowElementsText,
                "Row elements should look as follows: \n" + rowElementsText);
    }
}
