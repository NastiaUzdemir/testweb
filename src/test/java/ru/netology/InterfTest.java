package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InterfTest {
    @Test
    void shouldTestSomething() throws InterruptedException {
        // закгрузить страницу
        // поиск элементов
        // взаимодействие с элементами
        Selenide.open("http://0.0.0.0:9999");

        $("input[type=text]").setValue("Иванов Иван");
        $("input[type=tel]").setValue("+79094397835");
        $("[data-test-id=agreement]").click();
        SelenideElement continueButton =
                $(".form-field_theme_alfa-on-white .button__text").shouldBe(visible);
        continueButton.click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка " +
                "успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

        Thread.sleep(5000);
    }

    @Test
    public void testFieldValidation() throws InterruptedException {
        Selenide.open("http://0.0.0.0:9999");

        $("input[type=text]").setValue("");
        $("input[type=tel]").setValue("123");

        SelenideElement continueButton =
                $(".form-field_theme_alfa-on-white .button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $(byAttribute("data-test-id", "name")).
                $(".input__sub").shouldHave(text("Поле обязательно для заполнения"));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $(byAttribute("data-test-id", "phone")).
                shouldNotHave(cssClass("input_error"));

        Thread.sleep(5000);
    }
}
