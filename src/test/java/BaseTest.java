import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.PaymentsPage;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static HomePage homePage;
    protected static PaymentsPage paymentsPage;

    public final static String TEST_PHONE_NUMBER = "297777777";
    public final static String TEST_SUM = "5";
    public final static String TEST_EMAIL = "test@test.com";

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.mts.by/");
        homePage = new HomePage(driver);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
