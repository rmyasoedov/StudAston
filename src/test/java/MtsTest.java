import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HelperPage;
import pages.HomePage;
import pages.PaymentsPage;

import java.time.Duration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.mts.by/");
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Проверка пополнения баланса")
    public void testPaymentsPhoneNumber() {
        homePage.verifyTitleBlock();
        homePage.verifyLogoPayments();

        HelperPage helperPage = homePage.clickOnPaymentLink();
        helperPage.goBack();

        homePage.fillIncorrectPaymentForm();
        PaymentsPage paymentsPage = homePage.fillPaymentForm("297777777", "5", "test@test.com");
        paymentsPage
                .checkPlaceholderIframe()
                .checkLogoPayments();
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
