package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

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

    public PaymentsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getDescriptionText(){
        return getWaitingElement(payDescriptionLocator).getText();
    }

    public String getSumText(){
        return getWaitingElement(payCostLocator).getText();
    }

    public String getButtonText(){
        return getWaitingElement(payButtonLocator).getText();
    }

    public String getCardNumberPlaceholder(){
        return getPlaceholderElement(cardNumberLocator);
    }

    public String getExpirationDatePlaceholder(){
        return getPlaceholderElement(expirationDateLocator);
    }

    public String getCvcPlaceholder(){
        return getPlaceholderElement(cvcLocator);
    }

    public String getNameCardPlaceholder(){
        return getPlaceholderElement(nameCardLocator);
    }

    private String getPlaceholderElement(By element){
        return getWaitingElement(element).getText();
    }

    private List<WebElement> getLogos(){
        return driver.findElements(logoPaymentsLocator);
    }

    public int getAllLogosSize (){
        return getLogos().size();
    }

    public long getDisplayedLogosSize(){
        return getLogos().stream().filter(WebElement::isDisplayed).count();
    }

    private WebElement getWaitingElement(By element){
        try{
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch (TimeoutException e){
            throw new AssertionError("Не найден элемент: "+element);
        }
    }
}
