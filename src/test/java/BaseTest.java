import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected static WebDriver driver;
    protected final static String HOME_PAGE = "https://www.mts.by/";
    protected final static String HOME_TITLE = "МТС – мобильный оператор в Беларуси";
    protected final static String HELPER_PAGE_TITLE = "Порядок оплаты и безопасность интернет платежей";

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(HOME_PAGE);
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void doneCookie() {
        var cookieButton = driver.findElements(By.id("cookie-agree"));
        if (!cookieButton.isEmpty() && cookieButton.get(0).isDisplayed()) {
            cookieButton.get(0).click();
        }
    }
}
