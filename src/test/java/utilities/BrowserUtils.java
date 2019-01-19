package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowserUtils {

    public static boolean verifyTextMatches(String str1, String str2) {
        return str1.equals(str2);
    }

    public static boolean verifyTextContains(String str1, String str2) {
        return str1.contains(str2);
    }

    /**
     * Generates random date and month as card expiration.
     *
     * @return  String in a MM/YY format
     */
    public static String generateDateMM$YY() {
        int month = (int) (1 + Math.random() * 12);
        int year = (int) (19 + Math.random() * 8);

        return ((month < 10) ? "0" + month : month) + "/" + year;
    }

    /**
     * Generates a string of texts of all elements matching the By object.
     * Utilizes .getText() method.
     *
     * @param by        pass By variable
     * @return          returns String representation of all element texts
     */
    public static String toStringWebElements(By by) {
        WebDriver driver = Driver.getDriver();
        String retStr = "";

        List<WebElement> elements = driver.findElements(by);
        for (WebElement each : elements)
            retStr += "[ " + each.getText() + " ]";

        return retStr;
    }

}
