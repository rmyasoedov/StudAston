package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Field;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String HOME_TITLE = "МТС – мобильный оператор в Беларуси";
    private final By titleBlockLocator = By.xpath("//div[contains(@class, 'pay__wrapper')]//h2");
    private final By logoLocator = By.xpath("//div[contains(@class, 'pay__partners')]//img");
    private final By linkLocator = By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(text(), 'Подробнее о сервисе')]");

    private final By inputPhoneConnection = By.id("connection-phone");
    private final By inputSumConnection = By.id("connection-sum");
    private final By inputEmailConnection = By.id("connection-email");
    private final By buttonConnection = By.cssSelector("#pay-connection button.button__default[type='submit']");

    private final By iframeLocator = By.xpath("//iframe[contains(@class, 'bepaid-iframe')]");
    private final By cookieButtonLocator = By.id("cookie-agree");
    private final By selectButton = By.cssSelector(".select__header");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitlePaymentBlock() {
        WebElement h2Element = driver.findElement(titleBlockLocator);
        return h2Element.getText();
    }

    public List<String> getDisplayedLogos() {
        return driver.findElements(logoLocator)
                .stream()
                .filter(WebElement::isDisplayed)
                .map(img -> img.getAttribute("alt"))
                .collect(Collectors.toList());
    }

    public int getLogosCount() {
        return driver.findElements(logoLocator).size();
    }

    public WebElement helperLink(){
        return wait.until(ExpectedConditions.elementToBeClickable(linkLocator));
    }

    public String getHelperLinkText(){
        return helperLink().getText();
    }

    public boolean isShowHelperLink(){
        return helperLink().isDisplayed();
    }

    public boolean isEnabledHelperLink(){
        return helperLink().isDisplayed();
    }

    public void clickHelperLink(){
        helperLink().click();
    }

    public void scrollToLink(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", helperLink());
    }

    public String getHomeUrl(){
        return driver.getCurrentUrl();
    }

    public HelperPage getHelperPage(String prevUrl){
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(prevUrl)));
        return new HelperPage(driver);
    }

    public void waitForPageToLoad(){
        wait.until(ExpectedConditions.titleContains(HOME_TITLE));
        doneCookie();
    }

    public String getPlaceholderElement(Field field){
        return driver.findElement(field.getField()).getAttribute("placeholder");
    }

    public void clickButtonContinue(String formId){
        By submitButton = By.cssSelector("#"+formId+" button.button__default[type='submit']");
        driver.findElement(submitButton).click();
    }

    public void selectOption(String type){
        driver.findElement(selectButton).click();
        WebElement option = driver.findElement(By.xpath("//li[contains(@class, 'select__item')]/p[text()='"+type+"']"));
        option.click();
    }

    private List<WebElement> errorMessages(Field field){
        return
           driver.findElements(By.xpath("//input[@id='" + field.id + "']/parent::div//p"));
    }

    public int getCountErrors(Field field){
        return errorMessages(field).size();
    }

    public String getTextErrorMessage(Field field, int index){
        return errorMessages(field).get(index).getText();
    }

    public void fillPaymentForm(String phone, String sum, String email){
        overwriteField(inputPhoneConnection, phone);
        overwriteField(inputSumConnection, sum);
        overwriteField(inputEmailConnection, email);
    }

    public void clickButtonConnection(){
        driver.findElement(buttonConnection).click();
    }

    public boolean loadPaymentsPage(){
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
        }catch (TimeoutException e){
            return false;
        }
        return true;
    }

    public void overwriteField(Field field, String value){
        overwriteField(field.getField(), value);
    }

    public void overwriteField(By field, String value){
        try{
            driver.findElement(field).clear();
            driver.findElement(field).sendKeys(value);
        }catch (NoSuchElementException e){
            fail("Не найден элемент: "+field);
        }
    }

    public void doneCookie() {
        List<WebElement> cookieButton = driver.findElements(cookieButtonLocator);
        if (!cookieButton.isEmpty() && cookieButton.get(0).isDisplayed()) {
            cookieButton.get(0).click();
        }
    }
}
