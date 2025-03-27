import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MtsTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Проверка заголовка блока")
    public void testTitleBlock() {
        WebElement h2Element = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//h2"));
        String actualText = h2Element.getText();
        String expectedText = "Онлайн пополнение\nбез комиссии";
        assertEquals(expectedText, actualText, "Текст блока не соответствует");
    }

    @Test
    @Order(2)
    @DisplayName("Проверка логотипов платежных систем")
    public void testLogoPayments() {
        var needsLogo = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");
        List<WebElement> images = driver.findElements(By.xpath("//div[contains(@class, 'pay__partners')]//img"));
        String[] altArray = images.stream().map(img -> img.getAttribute("alt")).toArray(String[]::new);

        assertEquals(needsLogo.size(), altArray.length, "Не соответствует количество логотипов");
        assertTrue(images.stream().allMatch(WebElement::isDisplayed), "Не все логотипы видны");
        for (String logo : needsLogo) {
            assertTrue(Arrays.asList(altArray).contains(logo), "Нет лого: " + logo);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void testLink() {
        String needLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        WebElement link = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(text(), 'Подробнее о сервисе')]"));
        String href = link.getAttribute("href");

        assertEquals(needLink, href, "Ссылка ведёт не туда!");
        assertTrue(link.isDisplayed(), "Ссылка не отображается");
        assertTrue(link.isEnabled(), "Ссылка неактивна");

        doneCookie();
        String currentUrl = driver.getCurrentUrl();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        link.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        } catch (Exception e) {
            Assertions.fail("Страница не открылась");
        }

        assertEquals(HELPER_PAGE_TITLE, driver.getTitle(), "Открылась не та страница");
        driver.navigate().back();
    }

    @Test
    @Order(4)
    @DisplayName("Проверка формы оплаты")
    public void testPay() {
        WebDriverWait waitLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitLoad.until(ExpectedConditions.titleContains(HOME_TITLE));
        doneCookie();

        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).sendKeys("5");
        driver.findElement(By.id("connection-email")).sendKeys("test@test.com");

        driver.findElement(By.cssSelector("#pay-connection button.button__default[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")));
    }
}