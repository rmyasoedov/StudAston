package pages;

import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperPage {
    private final WebDriver driver;
    private final String expectedTitle = "Порядок оплаты и безопасность интернет платежей";

    public HelperPage(WebDriver driver) {
        this.driver = driver;
        verifyTitle();
    }

    private void verifyTitle() {
        assertEquals(expectedTitle, driver.getTitle(), "Открылась не та страница");
    }

    public void goBack() {
        driver.navigate().back();
    }
}
