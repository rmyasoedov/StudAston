import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import pages.HelperPage;
import pages.PaymentsPage;
import utils.Convertor;
import utils.Field;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MtsTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Проверка заголовка блока")
    public void testTitleBlock(){
        String expectedText = "Онлайн пополнение\nбез комиссии";
        String actualText = homePage.getTitlePaymentBlock();
        assertEquals(expectedText, actualText, "Текст блока не соответствует");
    }

    @Test
    @Order(2)
    @DisplayName("Проверка логотипов платежных систем")
    public void testLogoPayments(){
        List<String> expectedLogos = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");
        List<String> displayedLogos = homePage.getDisplayedLogos();

        assertEquals(expectedLogos.size(), homePage.getLogosCount(), "Не соответствует количество логотипов");
        assertTrue(displayedLogos.containsAll(expectedLogos), "Не все ожидаемые логотипы найдены");
    }

    @Test
    @Order(3)
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void testLink(){
        homePage.doneCookie();
        assertEquals("Подробнее о сервисе", homePage.getHelperLinkText());
        assertTrue(homePage.isShowHelperLink(), "Ссылка не отображается");
        assertTrue(homePage.isEnabledHelperLink(), "Ссылка неактивна");
        homePage.scrollToLink();
        String currentUrl = homePage.getHomeUrl();
        homePage.clickHelperLink();
        HelperPage helperPage = homePage.getHelperPage(currentUrl);
        assertEquals(HelperPage.expectedTitle, helperPage.getTitlePage(), "Открылась не та страница");
        helperPage.goBack();
    }

    @Test
    @Order(4)
    @DisplayName("Проверка значений текстовых полей и ошибок")
    public void testFieldsPlaceholdersAndErrors(){
        homePage.waitForPageToLoad();
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
        homePage.selectOption(option);

        homePage.overwriteField(numberField, "0");
        homePage.overwriteField(sumField, "0");
        homePage.overwriteField(emailField, "0");

        homePage.clickButtonContinue(formId);
        for(Field field : List.of(numberField,sumField,emailField)){
            assertEquals(field.placeholder, homePage.getPlaceholderElement(field), "Неверный placeholder");
            assertEquals(field.errors.size(), homePage.getCountErrors(field), "Количество ошибок не совпадает");
            for (int i = 0; i < field.errors.size(); i++){
                assertEquals(field.errors.get(i),homePage.getTextErrorMessage(field, i));
            }
        }
    }

    @Test
    @Order(5)
    @DisplayName("Проверка загрузки платежного окна")
    public void testLoadPaymentsModal(){
        homePage.doneCookie();
        homePage.selectOption("Услуги связи");
        homePage.fillPaymentForm(TEST_PHONE_NUMBER, TEST_SUM, TEST_EMAIL);
        homePage.clickButtonConnection();
        assertTrue(homePage.loadPaymentsPage(),"Iframe оплаты счета не найден");
    }

    @Test
    @Order(6)
    @DisplayName("Проверка переданных данных")
    public void testVerifySendData(){
        paymentsPage = new PaymentsPage(driver);
        assertEquals("Оплата: Услуги связи Номер:375"+TEST_PHONE_NUMBER, paymentsPage.getDescriptionText());
        assertEquals(Convertor.formatAmount(TEST_SUM) + " BYN", paymentsPage.getSumText());
        assertEquals("Оплатить "+Convertor.formatAmount(TEST_SUM)+" BYN", paymentsPage.getButtonText());
    }

    @Test
    @Order(7)
    @DisplayName("Проверка placeholder в iframe")
    public void testPlaceholderIframe(){
        assertEquals("Номер карты", paymentsPage.getCardNumberPlaceholder());
        assertEquals("Срок действия", paymentsPage.getExpirationDatePlaceholder());
        assertEquals("CVC", paymentsPage.getCvcPlaceholder());
        assertEquals("Имя держателя (как на карте)", paymentsPage.getNameCardPlaceholder());
    }

    @Test
    @Order(8)
    @DisplayName("Проверка лого ПС в Iframe")
    public void testLogoIframe(){
        assertEquals(5, paymentsPage.getAllLogosSize());
        assertEquals(4, paymentsPage.getDisplayedLogosSize());
    }
}
