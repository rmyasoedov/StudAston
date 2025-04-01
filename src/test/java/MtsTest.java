import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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
    @Epic("Главная")
    @Description("Проверяем, что есть нужный заголовок с правильным наименованием")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка заголовка блока")
    public void testTitleBlock(){
        String expectedText = "Онлайн пополнение\nбез комиссии";
        String actualText = homePage.getTitlePaymentBlock();
        Allure.step("Проверяем текст блока", () ->
           assertEquals(expectedText, actualText, "Текст блока не соответствует")
        );
    }

    @Test
    @Order(2)
    @Epic("Главная")
    @Description("Проверяем, что есть нужные логотипы платежных систем")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка логотипов платежных систем")
    public void testLogoPayments(){
        List<String> expectedLogos = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");
        List<String> displayedLogos = Allure.step("Получаем список логотипов", ()->homePage.getDisplayedLogos());

        Allure.step("Сверяем количество логотипов", ()->
             assertEquals(expectedLogos.size(), homePage.getLogosCount(), "Не соответствует количество логотипов")
        );
        Allure.step("Проверяем, все ли нужные логотипы есть", ()->
                assertTrue(displayedLogos.containsAll(expectedLogos), "Не все ожидаемые логотипы найдены")
        );
    }

    @Test
    @Order(3)
    @Epic("Подробнее о сервисе")
    @Description("Проверяем работу ссылки 'Подробнее о сервисе'")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void testLink(){
        homePage.doneCookie();
        Allure.step("Проверяем текст ссылки", ()->
                assertEquals("Подробнее о сервисе", homePage.getHelperLinkText())
        );

        Allure.step("Проверяем видимость ссылки", ()->
                assertTrue(homePage.isShowHelperLink(), "Ссылка не отображается")
        );
        Allure.step("Проверяем активность ссылки", ()->
                assertTrue(homePage.isEnabledHelperLink(), "Ссылка неактивна")
        );
        Allure.step("Делаем скроллинг до ссылки", ()->
                homePage.scrollToLink()
        );
        String currentUrl = Allure.step("Получение ссылки текущей страницы",()-> homePage.getHomeUrl());

        Allure.step("Нажатие на ссылку",()->
                homePage.clickHelperLink()
        );
        HelperPage helperPage = Allure.step("Получение ссылки страницы помощника",()->
            homePage.getHelperPage(currentUrl)
        );
        Allure.step("Проверяем, что открыласть правильная страница", ()->
                assertEquals(HelperPage.expectedTitle, helperPage.getTitlePage(), "Открылась не та страница")
        );
        helperPage.goBack();
    }

    @Test
    @Order(4)
    @Epic("Главная")
    @Description("Проверяем названия полей и текстов ошибок")
    @Severity(SeverityLevel.NORMAL)
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
        Allure.step("Выбираем категорию оплаты '"+option+"'", ()->
                homePage.selectOption(option)
        );

        for(Field field : List.of(numberField,sumField,emailField)){
            Allure.step("Проверяем placeholder '"+ field.placeholder +"'", ()->
                    assertEquals(field.placeholder, homePage.getPlaceholderElement(field), "Неверный placeholder")
            );
        }

        homePage.overwriteField(numberField, "0");
        homePage.overwriteField(sumField, "0");
        homePage.overwriteField(emailField, "0");

        homePage.clickButtonContinue(formId);
        for(Field field : List.of(numberField,sumField,emailField)){
            assertEquals(field.errors.size(), homePage.getCountErrors(field), "Количество ошибок не совпадает");
            for (int i = 0; i < field.errors.size(); i++){
                int finalI = i;
                Allure.step("Проверяем ошибки поля '"+ field.placeholder +"'", ()->
                        assertEquals(field.errors.get(finalI),homePage.getTextErrorMessage(field, finalI))
                );
            }
        }
    }

    @Test
    @Order(5)
    @Epic("Главная")
    @Description("Проверка загрузки платежного окна")
    @Severity(SeverityLevel.NORMAL)
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
    @Epic("Форма оплаты")
    @Description("Проверка переданных данных")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка переданных данных")
    public void testVerifySendData(){
        paymentsPage = new PaymentsPage(driver);
        assertEquals("Оплата: Услуги связи Номер:375"+TEST_PHONE_NUMBER, paymentsPage.getDescriptionText());
        assertEquals(Convertor.formatAmount(TEST_SUM) + " BYN", paymentsPage.getSumText());
        assertEquals("Оплатить "+Convertor.formatAmount(TEST_SUM)+" BYN", paymentsPage.getButtonText());
    }

    @Test
    @Order(7)
    @Epic("Форма оплаты")
    @Description("Проверка placeholder в iframe")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка placeholder в iframe")
    public void testPlaceholderIframe(){
        assertEquals("Номер карты", paymentsPage.getCardNumberPlaceholder());
        assertEquals("Срок действия", paymentsPage.getExpirationDatePlaceholder());
        assertEquals("CVC", paymentsPage.getCvcPlaceholder());
        assertEquals("Имя держателя (как на карте)", paymentsPage.getNameCardPlaceholder());
    }

    @Test
    @Order(8)
    @Epic("Форма оплаты")
    @Description("Проверка лого ПС в Iframe")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка лого ПС в Iframe")
    public void testLogoIframe(){
        assertEquals(5, paymentsPage.getAllLogosSize());
        assertEquals(4, paymentsPage.getDisplayedLogosSize());
    }
}
