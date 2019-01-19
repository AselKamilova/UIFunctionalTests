package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;
public class OrderPage {
    public OrderPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private String allProductsPageTitle = "Web Orders";
    private String allProductsPageURL = "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Process.aspx";

    @FindBy(css = "h1:First-of-type")
    public WebElement pageName;

    @FindBy (partialLinkText = "orders")
    public WebElement goToAllOrdersPage;

    @FindBy (partialLinkText = "products")
    public WebElement goToProductsPage;

    @FindBy (linkText = "Order")
    public WebElement goToOrderPage$Refresh;

    @FindBy (xpath = "//div[contains(text(), 'Welcome')]")
    public WebElement userNameDisplayed;

    @FindBy (id = "ctl00_logout")
    public WebElement logoutButton;

    @FindBy (id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement qtyInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
    public WebElement pricePerUnitInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_txtDiscount")
    public WebElement discountInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_txtTotal")
    public WebElement totalInput;

    @FindBy (css = "input[value='Calculate']")
    public WebElement calculateButton;

    @FindBy (id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerNameInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement streetAddressInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement cityInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement stateInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement visaRadioButton;

    @FindBy (id = "ctl00_MainContent_fmwOrder_cardList_1")
    public WebElement mastercardRadioButton;

    @FindBy (id = "ctl00_MainContent_fmwOrder_cardList_2")
    public WebElement americanexpressRadioButton;

    @FindBy (id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumberInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expirationDateInput;

    @FindBy (id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy (id = "ctl00_MainContent_fmwOrder_UpdateButton")
    public WebElement updateButton;

    @FindBy (css = "input[value='Reset']")
    public WebElement resetButton;

    public Select getProductList() {
        return new Select(Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
    }

    /**
     * Takes an integer and selects card type accordingly.
     * If index doesn't match exact card name, default type is Visa
     *
     * @param index 1 (one) based index => 1 for Visa, 2 for Master, 3 for Amex
     */
    public void selectCardByIndex(int index) {
        switch (index) {
            case 2: mastercardRadioButton.click(); break;
            case 3: americanexpressRadioButton.click(); break;
            default: visaRadioButton.click(); break;
        }
    }

    /**
     * Takes in a driver element to proceed as of the moment the method is called.
     * Returns the String name of the card type selected by the user.
     *
     * @param driver    pass in a driver element
     * @return          name of the selected card option
     */
    public String getSelectedCardName(WebDriver driver) {
        if (visaRadioButton.isSelected())
            return "Visa";
        else if (mastercardRadioButton.isSelected())
            return "MasterCard";
        else if (americanexpressRadioButton.isSelected())
            return "American Express";
        else
            return "";
    }

    public String getAllProductsPageTitle() {
        return allProductsPageTitle;
    }

    public String getAllProductsPageURL() {
        return allProductsPageURL;
    }
}
