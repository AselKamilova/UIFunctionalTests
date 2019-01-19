package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigurationReader;
import utilities.Driver;
public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private String loginPageTitle = "Web Orders Login";
    private String loginPageURL = ConfigurationReader.getProperty("url");

    @FindBy (id = "ctl00_MainContent_username")
    public WebElement loginUsername;

    @FindBy (id = "ctl00_MainContent_password")
    public WebElement loginPassword;

    @FindBy (id = "ctl00_MainContent_login_button")
    public WebElement loginSubmitButton;

    /**
     * Login method with editable username and password
     * Utilizable for negative tests
     *
     * @param username;
     * @param password
     */
    public void login(String username, String password) {

        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
        loginSubmitButton.click();
    }

    /**
     * Login method with configuration.properties provided username and password
     * Utilizable within other methods or within positive/happy-path tests
     */
    public void login(WebDriver driver) {
        driver.get(ConfigurationReader.getProperty("url"));
        loginUsername.sendKeys(ConfigurationReader.getProperty("username"));
        loginPassword.sendKeys(ConfigurationReader.getProperty("password"));
        loginSubmitButton.click();
    }

    public String getLoginPageTitle() {
        return loginPageTitle;
    }

    public String getLoginPageURL() {
        return loginPageURL;
    }
}
