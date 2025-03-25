package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void verifyTitleBlock() {
        WebElement h2Element = driver.findElement(titleBlockLocator);
        String actualText = h2Element.getText();
        String expectedText = "Онлайн пополнение\nбез комиссии";
        assertEquals(expectedText, actualText, "Текст блока не соответствует");
    }

    public void verifyLogoPayments() {
        var expectedLogos = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");
        List<WebElement> logos = driver.findElements(logoLocator);
        String[] altArray = logos.stream().map(img -> img.getAttribute("alt")).toArray(String[]::new);
        assertEquals(expectedLogos.size(), altArray.length, "Не соответствует количество логотипов");
        assertTrue(logos.stream().allMatch(WebElement::isDisplayed), "Не все логотипы видны");
        for (String logo : expectedLogos) {
            assertTrue(List.of(altArray).contains(logo), "Нет лого: " + logo);
        }
    }

    public HelperPage clickOnPaymentLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkLocator));
        assertTrue(link.isDisplayed(), "Ссылка не отображается");
        assertTrue(link.isEnabled(), "Ссылка неактивна");

        acceptCookies();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(link.isDisplayed(), "Ссылка не видна");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        link.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        return new HelperPage(driver);
    }

    public void fillIncorrectPaymentForm() {
        wait.until(ExpectedConditions.titleContains(HOME_TITLE));
        acceptCookies();

        formFields(
                "Домашний интернет",
                new Field("internet-phone","Номер абонента", List.of("Необходимо указать номер в формате +375 00 ХХХ-ХХ-ХХ")),
                new Field("internet-sum", "Сумма",List.of("Введите сумму платежа")),
                new Field("internet-email", "E-mail для отправки чека",List.of("Введите корректный адрес электронной почты.")),
                "pay-internet"
        );

        formFields(
                "Услуги связи",
                new Field("connection-phone", "Номер телефона",List.of("Номер телефона указан неверно","Введите номер телефона")),
                new Field("connection-sum", "Сумма",List.of("Введите сумму платежа")),
                new Field("connection-email", "E-mail для отправки чека",List.of("Введите корректный адрес электронной почты.")),
                "pay-connection"
        );

        formFields(
                "Рассрочка",
                new Field("score-instalment", "Номер счета на 44",List.of("Введите корректный номер лицевого счета")),
                new Field("instalment-sum", "Сумма",List.of("Введите сумму платежа")),
                new Field("instalment-email", "E-mail для отправки чека",List.of("Введите корректный адрес электронной почты.")),
                "pay-instalment"
        );

        formFields(
                "Задолженность",
                new Field("score-arrears", "Номер счета на 2073",List.of("Введите корректный номер лицевого счета")),
                new Field("arrears-sum", "Сумма",List.of("Введите сумму платежа")),
                new Field("arrears-email", "E-mail для отправки чека",List.of("Введите корректный адрес электронной почты.")),
                "pay-arrears"
        );
    }

    private void formFields(String option, Field numberField, Field sumField, Field emailField, String formId){
        selectOption(option);
        By submitButton = By.cssSelector("#"+formId+" button.button__default[type='submit']");

        overwriteField(numberField.getField(), "0");
        overwriteField(sumField.getField(), "0");
        overwriteField(emailField.getField(), "0");

        assertEquals(numberField.placeholder,  driver.findElement(numberField.getField()).getAttribute("placeholder"));
        assertEquals(sumField.placeholder,  driver.findElement(sumField.getField()).getAttribute("placeholder"));
        assertEquals(emailField.placeholder,  driver.findElement(emailField.getField()).getAttribute("placeholder"));
        driver.findElement(submitButton).click();
        checkMessage(numberField);
        checkMessage(sumField);
        checkMessage(emailField);
    }

    private void selectOption(String type){
        driver.findElement(selectButton).click();
        WebElement option = driver.findElement(By.xpath("//li[contains(@class, 'select__item')]/p[text()='"+type+"']"));
        option.click();
    }

    private void checkMessage(Field field){
        List<WebElement> errorLocators = driver.findElements(By.xpath("//input[@id='" + field.id + "']/parent::div//p"));

        assertEquals(field.errors.size(), errorLocators.size(), "Количество ошибок не совпадает");
        for (int i = 0; i < field.errors.size(); i++) {
            assertEquals(field.errors.get(i), errorLocators.get(i).getText(), "Ошибка не совпадает");
        }
    }

    static class Field{
        final String id;
        final List<String> errors;
        final String placeholder;

        public Field(String fieldId, String placeholder, List<String> errors){
            this.id = fieldId;
            this.placeholder = placeholder;
            this.errors = errors;
        }

        public By getField(){ return By.id(id); }
    }


    public PaymentsPage fillPaymentForm(String phone, String sum, String email) {
        wait.until(ExpectedConditions.titleContains(HOME_TITLE));
        acceptCookies();
        selectOption("Услуги связи");

        overwriteField(inputPhoneConnection, phone);
        overwriteField(inputSumConnection, sum);
        overwriteField(inputEmailConnection, email);

        driver.findElement(buttonConnection).click();

        switchToIframe();

        return new PaymentsPage(driver, phone, sum, email);
    }

    private void overwriteField(By field, String value){
        try{
            driver.findElement(field).clear();
            driver.findElement(field).sendKeys(value);
        }catch (NoSuchElementException e){
            fail("Не найден элемент: "+field);
        }
    }

    private void acceptCookies() {
        List<WebElement> cookieButton = driver.findElements(cookieButtonLocator);
        if (!cookieButton.isEmpty() && cookieButton.get(0).isDisplayed()) {
            cookieButton.get(0).click();
        }
    }

    private void switchToIframe(){
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
        }catch (TimeoutException e){
            fail("Iframe оплаты счета не найден");
        }
    }
}
