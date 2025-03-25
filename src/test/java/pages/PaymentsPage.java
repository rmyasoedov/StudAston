package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Convertor;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By payDescriptionLocator = By.cssSelector(".pay-description__text span");
    private final By payCostLocator = By.cssSelector(".pay-description__cost span");
    private final By payButtonLocator = By.cssSelector(".card-page__container button[type='submit']");

    private final By cardNumberLocator = By.xpath("//input[@id='cc-number']/parent::div//label");
    private final By expirationDateLocator = By.xpath("//input[@formcontrolname='expirationDate']/parent::div//label");
    private final By cvcLocator = By.xpath("//input[@formcontrolname='cvc']/parent::div//label");
    private final By nameCardLocator = By.xpath("//input[@formcontrolname='holder']/parent::div//label");

    private final By logoPaymentsLocator = By.cssSelector(".cards-brands__container img");

    public PaymentsPage(WebDriver driver, String phone, String sum, String email){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        verifySendData(phone, sum, email);
    }

    public PaymentsPage verifySendData(String phone, String sum, String email){
        var description = getWaitingElement(payDescriptionLocator);
        var cost = getWaitingElement(payCostLocator);
        var button = getWaitingElement(payButtonLocator);

        assertEquals(Convertor.formatAmount(sum) + " BYN", cost.getText());
        assertEquals("Оплата: Услуги связи Номер:375"+phone, description.getText());
        assertEquals("Оплатить "+Convertor.formatAmount(sum)+" BYN", button.getText());
        return this;
    }

    public PaymentsPage checkPlaceholderIframe(){
        checkPlaceholder(cardNumberLocator, "Номер карты");
        checkPlaceholder(expirationDateLocator, "Срок действия");
        checkPlaceholder(cvcLocator, "CVC");
        checkPlaceholder(nameCardLocator, "Имя держателя (как на карте)");
        return this;
    }

    public PaymentsPage checkLogoPayments(){
        List<WebElement> logos = driver.findElements(logoPaymentsLocator);
        assertEquals(5, logos.size(), "Неверное количество логотипов платежных систем");
        var totalDisplayed = logos.stream().filter(WebElement::isDisplayed).count();
        assertEquals(4, totalDisplayed, "Неверное количество видимых логотипов");
        return this;
    }

    private void checkPlaceholder(By fieldLocator, String needText){
        var field = driver.findElements(fieldLocator);
        assertFalse(field.isEmpty(), "Элемент не найден");
        assertTrue(field.get(0).isDisplayed(), "Элемент не отображается на экране");
        assertEquals(needText, field.get(0).getText());
    }

    private WebElement getWaitingElement(By element){
        try{
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch (TimeoutException e){
            throw new AssertionError("Не найден элемент: "+element);
        }
    }
}
