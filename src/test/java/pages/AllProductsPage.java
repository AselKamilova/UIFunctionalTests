package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

import java.util.List;

public class AllProductsPage {
    public AllProductsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private String allProductsPageTitle = "Web Orders";
    private String allProductsPageURL = "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Products.aspx";

    @FindBy(css = "h1:First-of-type")
    public WebElement pageName;

    @FindBy (partialLinkText = "orders")
    public WebElement goToAllOrdersPage;

    @FindBy (partialLinkText = "products")
    public WebElement goToProductsPage$Refresh;

    @FindBy (linkText = "Order")
    public WebElement goToOrderPage;

    @FindBy (xpath = "//div[contains(text(), 'Welcome')]")
    public WebElement userNameDisplayed;

    @FindBy (id = "ctl00_logout")
    public WebElement logoutButton;

    @FindBy (className = "ProductsTable")
    public WebElement productsTable;

    /**
     * Create and return the xPath of the row of the table requested
     * per the index number. This can be utilized to store elements of
     * a row within List<WebElement> to conduct further operations.
     *
     * @param row   1 (one) based index of the row
     * @return      xPath of the row requested
     */
    public static String getTableElementXPath(int row) {
        WebDriver driver = Driver.getDriver();

        List<WebElement> rows = driver.findElements(By.cssSelector(".ProductsTable tbody tr"));

        if (row > rows.size() - 1)
            row = rows.size() - 1 ;
        else if (row <= 0)
            row = 1;

        return "//tbody/tr[" + (row + 1) + "]";
    }

    /**
     * Create and return the xPath of the column of corresponding columnHeading.
     * This xPath should be passed into List<WebElement> so that all elements
     * within the column can be stored.
     *
     * @param columnHeading String of the column heading name
     * @return              xPath of the column
     */
    public static String getTableElementXPath(String columnHeading) throws NoSuchElementException {
        WebDriver driver = Driver.getDriver();

        List<WebElement> columns = driver.findElements(By.cssSelector("tr th"));

        for (int i = 1; i <= columns.size(); i++) {
            WebElement heading = driver.findElement(By.cssSelector("tr th:nth-child(" + i));
            String headText = heading.getText().trim();

            if (headText.contains(columnHeading))
                return "//*[@class='ProductsTable']//tr//td[" + i + "]";
        }

        throw new NoSuchElementException(columnHeading + " is not found among the table headings.");
    }

    /**
     * Create and return the xPath of the exact item from within the table that
     * corresponds the table heading and row number.
     * This xPath will point to a single cell of the table.
     *
     * @param columnHeading String of the column heading name
     * @param row           1 (one) based index of the row
     * @return              xPath of the requested table item
     */
    public static String getTableElementXPath(String columnHeading, int row) {
        String columnXPath = getTableElementXPath(columnHeading);
        String rowXPath = getTableElementXPath(row);

        int length = rowXPath.length();
        String editedRowNumber = rowXPath.substring(length-2, length-1);
        int rowNumber = Integer.parseInt(editedRowNumber);

        return "(" + columnXPath + ")" + "[" + (rowNumber-1) + "]";
    }


    public String getAllProductsPageTitle() {
        return allProductsPageTitle;
    }

    public String getAllProductsPageURL() {
        return allProductsPageURL;
    }
}
