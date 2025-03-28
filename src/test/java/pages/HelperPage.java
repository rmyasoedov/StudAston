package pages;

import org.openqa.selenium.WebDriver;

public class HelperPage {
    private final WebDriver driver;
    public final static String expectedTitle = "Порядок оплаты и безопасность интернет платежей";

    public HelperPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitlePage() {
        return driver.getTitle();
    }

    public void goBack() {
        driver.navigate().back();
    }
}
