package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class InterfTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.headless = true;
    }

    @Test
    void shouldTestSomething() throws InterruptedException {
        // закгрузить страницу
        // поиск элементов
        // взаимодействие с элементами
        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("Иванов Иван");
        $("input[type=tel]").setValue("+79094397835");
        $("[data-test-id=agreement]").click();
        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка " +
                "успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void testFieldValidationName() throws InterruptedException {
        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("");
        $("input[type=tel]").setValue("123");
        $("[data-test-id=agreement]").click();

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $("[data-test-id='name'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='phone'] .input__sub").shouldNotHave(cssClass("input_error"));

    }

    @Test
    public void testFieldValidationPhone() throws InterruptedException {

        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("Иван Иванов");
        $("input[type=tel]").setValue("");
        $("[data-test-id=agreement]").click();

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='name'] .input__sub").shouldNotHave(cssClass("input_error"));

    }

    @Test
    public void testFieldValidationPhoneName() throws InterruptedException {

        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("");
        $("input[type=tel]").setValue("");
        $("[data-test-id=agreement]").click();

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $("[data-test-id='phone'] .input__sub").shouldNotHave(cssClass("input_error"));
        $("[data-test-id='name'] .input__sub").shouldNotHave(cssClass("input_error"));
    }

    @Test
    public void testFieldValidationCbox() throws InterruptedException {
        // закгрузить страницу
        // поиск элементов
        // взаимодействие с элементами
        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("Иванов Иван");
        $("input[type=tel]").setValue("+79094397835");
        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        $("[data-test-id=agreement] .checkbox__text").shouldNotHave(cssClass("input_error"));
    }


}
