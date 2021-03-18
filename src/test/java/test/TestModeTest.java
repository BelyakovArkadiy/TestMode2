package test;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestModeTest{

    User user = new User();
    Faker faker = new Faker();

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
        faker = new Faker(new Locale("eng"));
        user = new User();

    }


    @Test
    void shouldActiveUser() {
        RegistrationDto.setActiveUser();
        $("[data-test-id=login] [class = input__control]").setValue(user.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldBlockedUser() {
        RegistrationDto.setBlockedUser();
        $("[data-test-id=login] [class = input__control]").setValue(user.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible,Duration.ofSeconds(5));

    }

    @Test
    void shouldIncorrectPassword() {
        RegistrationDto.setIncorrectPassword();
        $("[data-test-id=login] [class = input__control]").setValue(user.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(faker.internet().password());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible,Duration.ofSeconds(5));

    }

    @Test
    void shouldIncorrectLogin() {
        RegistrationDto.setIncorrectLogin();
        $("[data-test-id=login] [class = input__control]").setValue(faker.name().fullName());
        $("[data-test-id=password] [class = input__control]").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(5));

    }



}




