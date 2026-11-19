
package ru.netology.Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CallbackTest {

    @Test //Проверка визуального отображения текста и элементов ++
    void shouldBookCardSuccessfully() {
        // Открываем сайт
        open("http://localhost:9999");
        // Проверяем заголовок
        $("h2").should(Condition.text("Карта с доставкой!"))
        .should(Condition.visible);
        // Проверяем подсказку под полем Город
        $("[data-test-id=city] .input__sub")
                .should(Condition.text("Выберите ваш город"))
                .should(Condition.visible);
        // Проверяем placeholder поля Город
        $("[data-test-id=city] input")
                .should(Condition.attribute("placeholder", "Город"));
        // Устанавливаем указанное значение в поле Город
        $("[data-test-id=city] input").setValue("Мо");
        //Список появился
        $(".menu-item__control").shouldBe(visible);
        // Выбираем Москву из списка
        $$(".menu-item__control").find(Condition.exactText("Москва")).click();
        $("[data-test-id=city] input").should(Condition.value("Москва"));

        // Нажимаем на поле Дата
        $("[data-test-id=date] .input__sub").should(Condition.text("Выберите дату встречи с представителем банка"))
                .should(Condition.visible);
        // Проверяем отображение кнопки календаря
        $(".input__icon button").should(Condition.visible);
// Проверяем отображение иконки календаря
        $(".icon_name_calendar").should(Condition.visible);
        $("[data-test-id=date] input").click();
        // Проверяем, что календарь появился
        $(".calendar").shouldBe(visible);
        // Выбираем 23 ноября
        $$(".calendar__day").findBy(exactText("23")).click();
        $("[data-test-id=date] input").should(Condition.value("23.11.2026"));

        // Проверяем надпись над полем Имя
        $("[data-test-id=name] .input__top")
                .should(Condition.text("Фамилия и имя"))
                .should(Condition.visible);
        $("[data-test-id=name] .input__sub")
                .should(Condition.text("Укажите точно как в паспорте"))
                .should(Condition.visible);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=name] input").should(Condition.value("Иванов Иван"));

        // Проверяем надпись над полем Телефон
        $("[data-test-id=phone] .input__top")
                .should(Condition.text("Мобильный телефон"))
                .should(Condition.visible);

        // Проверяем подсказку под полем Телефон
        $("[data-test-id=phone] .input__sub")
                .should(Condition.text(
                        "На указанный номер моб. тел. будет отправлен " +
                                "смс-код для подтверждения заявки на карту. Проверьте, " +
                                "что номер ваш и введен корректно."))
                .should(Condition.visible);
        $("[data-test-id=phone] input").setValue("+79234443322");
        $("[data-test-id=phone] input").shouldHave(value("+79234443322"));


        // Проверяем, что область флажка отображается
        $(".checkbox__box").should(Condition.visible);
        // Проверяем, что галочка изначально не установлена
        $(".checkbox__control").should(Condition.not(Condition.selected));
        $(".checkbox__text").should(Condition.text(
                "Я соглашаюсь с условиями обработки и использования моих персональных данных"))
                .should(Condition.visible);
        // Ставим галочку согласия
        $("[data-test-id=agreement]").click();
        // Проверяем, что галочка установлена
        $(".checkbox__control").should(Condition.selected);

        // Проверяем отображение иконки безопасности
        $(".icon_name_category-guard").should(Condition.visible);

// Проверяем текст о безопасности данных
        $(".Order_guardText__yFCDY").should(Condition.text(
                        "Мы гарантируем безопасность ваших данных"))
                .should(Condition.visible);

        // Проверяем отображение кнопки
        $(".button").should(Condition.visible);
// Проверяем текст кнопки
        $(".button__text").should(Condition.text("Забронировать"))
                .should(Condition.visible);

        $$("button").find(Condition.exactText("Забронировать")).click();
        // Проверяем отображение элемента загрузки
        $(".button__icon .spin").should(Condition.visible);

        // Проверяем отображение уведомления
        $("[data-test-id=notification]")
                .should(Condition.visible, Duration.ofSeconds(15));

// Проверяем отображение иконки успешного бронирования
        //$(".icon_name_ok").should(Condition.visible);

// Проверяем заголовок уведомления
        $(".notification__title").should(Condition.text("Успешно!"));

// Проверяем текст уведомления
        $(".notification__content").should(Condition.text(
                "Встреча успешно забронирована на 23.11.2026"));
        // Проверяем отображение кнопки закрытия уведомления
        $(".notification__closer").should(Condition.visible);

// Проверяем отображение иконки крестика
        $(".notification__closer .icon_name_close").should(Condition.visible);

        // Нажимаем на крестик
        $(".notification__closer").click();

// Проверяем, что уведомление закрылось
        $("[data-test-id=notification]").should(Condition.not(Condition.visible));

    }
    @Test //Проверка визуального отображения уведомления об успешном бранировании
    void shouldBookCardSuccessfully1() {

    }

    @Test // Отправка заявки на бронирование
    void shouldBookCardSuccessfully2() {

    }

    @Test
    void shouldBookCardSuccessfully3() {
        // Открываем сайт
        open("http://localhost:9999");
        // Нажимаем на поле города
        $("[data-test-id=city] input").click();
        sleep(2000);
// Вводим город по символам
        $("[data-test-id=city] input").sendKeys("М");
        sleep(200);
        $("[data-test-id=city] input").sendKeys("о");
        sleep(2000);
// Выбираем Москву из списка
        $$(".menu-item__control").findBy(exactText("Москва")).click();
// Проверяем, что город выбран
        $("[data-test-id=city] input").shouldHave(value("Москва"));
        // Нажимаем на поле Дата
        $("[data-test-id=date] input").click();
// Выделяем старую дату
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        sleep(1000);
// Удаляем старую дату
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        // Открываем календарь
        $("[data-test-id=date] input").click();
        sleep(1000);
// Проверяем, что календарь появился
        $(".calendar").shouldBe(visible);

//$$(".calendar__day").findBy(exactText("23")).click(); / Для поиска по тексту

// Выбираем 23 ноября
        $$(".calendar__day").findBy(exactText("23")).click();

// Проверяем выбранную дату
        $("[data-test-id=date] input").shouldHave(value("23.11.2026"));
        // Нажимаем на поле Имя
        $("[data-test-id=name] input").click();
// Вводим имя по символам
        $("[data-test-id=name] input").sendKeys("И");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("в");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("а");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("н");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("о");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("в");
        sleep(200);
        $("[data-test-id=name] input").sendKeys(" ");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("И");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("в");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("а");
        sleep(200);
        $("[data-test-id=name] input").sendKeys("н");
// Проверяем введённое имя
        $("[data-test-id=name] input").shouldHave(value("Иванов Иван"));
        // Заполняем телефон
        // Нажимаем на поле телефона
        $("[data-test-id=phone] input").click();
// Вводим телефон по символам
        $("[data-test-id=phone] input").sendKeys("+");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("7");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("9");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("9");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("9");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("1");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("2");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("3");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("4");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("5");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("6");
        sleep(200);
        $("[data-test-id=phone] input").sendKeys("7");
// Проверяем введённый телефон
        $("[data-test-id=phone] input").shouldHave(value("+79991234567"));
        // Ставим галочку согласия
        $("[data-test-id=agreement]").click();
        // Нажимаем кнопку бронирования
        $$("button").findBy(exactText("Забронировать")).click();
        // Ждём 15 секунд, чтобы увидеть результат
        sleep(15000);
    }
}

