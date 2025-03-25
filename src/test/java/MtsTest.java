import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTest {

    private final static String HOME_PAGE = "https://www.mts.by/";
    private final static String HOME_TITLE = "МТС – мобильный оператор в Беларуси";

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(HOME_PAGE);
    }

    @Test
    @DisplayName("Проверка пополнения баланса")
    public void testPaymentsPhoneNumber() {
        testTitleBlock();
        testLogoPayments();
        testLink();
        testPay();
    }

    public void testTitleBlock() {
        WebElement h2Element = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//h2"));
        String actualText = h2Element.getText();
        String expectedText = "Онлайн пополнение\nбез комиссии";
        assertEquals(actualText, expectedText, "Текст блока не соответствует");
    }

    public void testLogoPayments(){
        var needsLogo = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");
        List<WebElement> images =
                driver.findElements(By.xpath("//div[contains(@class, 'pay__partners')]//img"));
        String[] altArray = images.stream()
                .map(img -> img.getAttribute("alt"))
                .toArray(String[]::new);


        assertEquals(needsLogo.size(), altArray.length, "Не соответствует количество логотипов");
        for(String logo : needsLogo){
            boolean status = Arrays.asList(altArray).contains(logo);
            assertTrue(status, "Нет лого: "+logo);
        }
    }

    private void testLink() {
        String needLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

        WebElement link = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(text(), 'Подробнее о сервисе')]"));
        String href = link.getAttribute("href");

        assertEquals(href, needLink, "Ссылка ведёт не туда!");

        assertTrue(link.isDisplayed(), "Ссылка не отображается");
        assertTrue(link.isEnabled(), "Ссылка неактивна");
        doneCookie();

        String currentUrl = driver.getCurrentUrl();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        link.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        }catch (Exception e){
            fail("Страница не открылась");
        }
        String expectedTitle = "Порядок оплаты и безопасность интернет платежей";
        assertEquals(expectedTitle, driver.getTitle(), "Открылась не та страница");
        driver.navigate().back();
    }

    private void testPay(){
        WebDriverWait waitLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitLoad.until(ExpectedConditions.titleContains(HOME_TITLE));
        doneCookie();
        var inputPhone = driver.findElement(By.id("connection-phone"));
        inputPhone.sendKeys("297777777");
        var inputSum = driver.findElement(By.id("connection-sum"));
        inputSum.sendKeys("5");
        var inputEmail = driver.findElement(By.id("connection-email"));
        inputEmail.sendKeys("test@test.com");

        var buttonContinue = driver.findElement(By.cssSelector("#pay-connection button.button__default[type='submit']"));
        buttonContinue.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")));
    }


    private void doneCookie(){
        var cookieButton = driver.findElements(By.id("cookie-agree"));

        if(!cookieButton.isEmpty() && cookieButton.get(0).isDisplayed()){
            cookieButton.get(0).click();
        }
    }


    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
