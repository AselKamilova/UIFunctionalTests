package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

import java.util.List;


public class AllOrdersPage {
    public AllOrdersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private String allOrdersPageTitle = "Web Orders";
    private String allOrdersPageURL = "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/";

    @FindBy (css = "h1:First-of-type")
    public WebElement pageName;

    @FindBy (partialLinkText = "orders")
    public WebElement goToAllOrders$Refresh;

    @FindBy (partialLinkText = "products")
    public WebElement goToProductsPage;

    @FindBy (xpath = "//*[@id='ctl00_menu']/li[3]/a")
    public WebElement goToOrderPage;

    @FindBy (xpath = "//div[contains(text(), 'Welcome')]")
    public WebElement userNameDisplayed;

    @FindBy (id = "ctl00_logout")
    public WebElement logoutButton;

    @FindBy (id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAllButton;

    @FindBy (id = "ctl00_MainContent_btnUncheckAll")
    public WebElement uncheckAllButton;

    @FindBy (id = "ctl00_MainContent_orderGrid")
    public WebElement ordersTable;

    @FindBy (id = "ctl00_MainContent_btnDelete")
    public WebElement deleteSelectedButton;

    /**
     * Create and return the xPath of the checkbox element requested
     * per the index number
     *
     * @param checkBoxIndex     1 (one) based index of the checkbox
     * @return                  xPath of the checkbox requested
     */
    public static String getCheckboxXPath(int checkBoxIndex) {
        WebDriver driver = Driver.getDriver();
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("[type='checkbox']"));

        if (checkBoxIndex > checkBoxes.size())
            checkBoxIndex = checkBoxes.size();
        else if (checkBoxIndex <= 0)
            checkBoxIndex = 1;

        return "(//*[@type='checkbox'])[" + checkBoxIndex + "]";
    }

    /**
     * Create and return the xPath of the edit button element requested
     * per the index number
     *
     * @param editButtonIndex     1 (one) based index of the checkbox
     * @return                  xPath of the checkbox requested
     */
    public static String getEditButtonXPath(int editButtonIndex) {
        WebDriver driver = Driver.getDriver();
        List<WebElement> editButtons = driver.findElements(By.cssSelector("[type='image']"));

        if (editButtonIndex > editButtons.size())
            editButtonIndex = editButtons.size();
        else if (editButtonIndex <= 0)
            editButtonIndex = 1;

        return "(//*[@type='image'])[" + editButtonIndex + "]";
    }

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

        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));

        if (row > rows.size() - 2)
            row = rows.size() - 2 ;
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
            if (headText.isEmpty())
                continue;

            if (headText.contains(columnHeading))
                return "//*[@class='SampleTable']//tr//td[" + i + "]";
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

    public String getAllOrdersPageTitle() {
        return allOrdersPageTitle;
    }

    public String getAllOrdersPageURL() {
        return allOrdersPageURL;
    }
}
