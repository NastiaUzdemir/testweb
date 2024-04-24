package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Sleeper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterfTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
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
        $("input[type=tel]").setValue("+79094397835");
        $("[data-test-id=agreement]").click();

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='phone'].input_invalid .input__sub").shouldNotBe(visible);

        Assertions.assertTrue($("[data-test-id='name'].input_invalid .input__sub").is(visible));
        Assertions.assertFalse($("[data-test-id='phone'].input_invalid .input__sub").is(visible));

    }
    @Test
    public void testInvalidName() throws InterruptedException {
        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("Ivan Ivanov");
        $("input[type=tel]").setValue("+79094397835");
        $("[data-test-id=agreement]").click();

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='phone'].input_invalid .input__sub").shouldNotBe(visible);

        Assertions.assertTrue($("[data-test-id='name'].input_invalid .input__sub").is(visible));
        Assertions.assertFalse($("[data-test-id='phone'].input_invalid .input__sub").is(visible));

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
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='name'].input_invalid .input__sub").shouldNotBe(visible);

        Assertions.assertTrue($("[data-test-id='phone'].input_invalid .input__sub").is(visible));
        Assertions.assertFalse($("[data-test-id='name'].input_invalid .input__sub").is(visible));

    }
    @Test
    public void testInvalidPhone() throws InterruptedException {

        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("Иван Иванов");
        $("input[type=tel]").setValue("34522");
        $("[data-test-id=agreement]").click();

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        // сообщение об ошибке
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='name'].input_invalid .input__sub").shouldNotBe(visible);

        Assertions.assertTrue($("[data-test-id='phone'].input_invalid .input__sub").is(visible));
        Assertions.assertFalse($("[data-test-id='name'].input_invalid").exists());

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
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));

        // Проверяем, что только первое неправильно заполненное поле подсвечено
        $("[data-test-id='phone'].input_invalid .input__sub").shouldNotBe(visible);

        Assertions.assertTrue($("[data-test-id='name'].input_invalid .input__sub").is(visible));
        Assertions.assertFalse($("[data-test-id='phone'].input_invalid").exists());

    }

    @Test
    public void testFieldValidationCbox() throws InterruptedException {

        Selenide.open("http://localhost:9999");

        $("input[type=text]").setValue("Иванов Иван");
        $("input[type=tel]").setValue("+79094397835");
        SelenideElement checkbox =
                $("[data-test-id=agreement] .checkbox__text").shouldBe(visible).shouldBe(enabled);

        Assertions.assertFalse(checkbox.isSelected());

        SelenideElement continueButton =
                $(".button__text").shouldBe(visible);
        continueButton.click();

        String redTextColor = checkbox.getCssValue("color");
        Assertions.assertEquals("rgba(255, 92, 92, 1)", redTextColor);

    }


}
