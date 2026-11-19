package ru.netology.Selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class CallbackPromoTest {

    @Test
    void shouldSubmitFormSuccessfully() {

        // Открываем сайт
        open("http://localhost:9999");

        // Получаем текущую дату и прибавляем 5 дней
        LocalDate meetingDate = LocalDate.now().plusDays(5);

        // Форматируем дату для HTML-поля
        String dateForInput = meetingDate.toString();

        // Форматируем дату для отображения
        String formattedDate = meetingDate.format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        // Вводим город напрямую
        $("[data-test-id=city] input").setValue("Москва");

        // Проверяем введённый город
        $("[data-test-id=city] input")
                .should(Condition.value("Москва"));

        // Нажимаем на поле Дата
        $("[data-test-id=date] input").click();
// Выделяем старую дату
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
// Удаляем старую дату
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);

        // Вводим дату
        $("[data-test-id=date] input.input__control")
                .setValue(formattedDate);

// Проверяем установленную дату
        $("[data-test-id=date] input.input__control")
                .should(Condition.value(formattedDate));

        // Проверяем дату в видимом поле
        $("[data-test-id=date] .input__control")
                .should(Condition.value(formattedDate));

        // Вводим фамилию и имя
        $("[data-test-id=name] input")
                .setValue("Иванов Иван");

// Проверяем введённое имя
        $("[data-test-id=name] input")
                .should(Condition.value("Иванов Иван"));

        // Вводим номер телефона
        $("[data-test-id=phone] input")
                .setValue("+79991234567");

// Проверяем введённый номер
        $("[data-test-id=phone] input")
                .should(Condition.value("+79991234567"));

        // Проверяем, что галочка ещё не установлена
        $("[data-test-id=agreement] input")
                .shouldNot(Condition.selected);

// Устанавливаем галочку согласия
        $("[data-test-id=agreement]").click();

// Проверяем, что галочка установлена
        $("[data-test-id=agreement] input")
                .shouldBe(Condition.selected);

        $$("button").find(Condition.exactText("Забронировать")).click();

        // Проверяем отображение элемента загрузки
        $(".button__icon .spin").should(Condition.visible);

        // Проверяем отображение уведомления
        $("[data-test-id=notification]")
                .should(Condition.visible, Duration.ofSeconds(15));

// Проверяем завершение загрузки не более чем за 15 секунд
        $(".button__icon .spin")
                .should(Condition.disappear, Duration.ofSeconds(15));

// Проверяем заголовок уведомления
        $(".notification__title").should(Condition.text("Успешно!"));

// Проверяем текст уведомления
        $(".notification__content").should(Condition.text(
                "Встреча успешно забронирована на " + formattedDate));

        // Проверяем отображение кнопки закрытия уведомления
        $(".notification__closer").should(Condition.visible);

// Проверяем отображение иконки крестика
        $(".notification__closer .icon_name_close").should(Condition.visible);

        // Нажимаем на крестик
        $(".notification__closer").click();

// Проверяем, что уведомление закрылось
        $("[data-test-id=notification]").should(Condition.not(Condition.visible));

    }

    @Test
    void shouldBookCardSuccessfully3() {
        // Открываем сайт
        open("http://localhost:9999");
        // Нажимаем на поле города
        $("[data-test-id=city] input").click();


// Вводим город по символам
        $("[data-test-id=city] input").sendKeys("М");

        $("[data-test-id=city] input").sendKeys("о");

// Выбираем Москву из списка
        $$(".menu-item__control").findBy(exactText("Москва")).click();
// Проверяем, что город выбран
        $("[data-test-id=city] input").shouldHave(value("Москва"));
        // Нажимаем на поле Дата
        $("[data-test-id=date] input").click();
// Выделяем старую дату
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");

// Удаляем старую дату
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        // Открываем календарь
        $("[data-test-id=date] .icon-button").click();

// Проверяем, что календарь появился
        $(".calendar").shouldBe(Condition.visible);

//$$(".calendar__day").findBy(exactText("23")).click(); / Для поиска по тексту
// Вычисляем дату через неделю
        LocalDate meetingDate = LocalDate.now().plusWeeks(1);

// Получаем timestamp в миллисекундах
        long meetingTimestamp = meetingDate
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

// Выбираем нужную дату в календаре
        $$(".calendar__day")
                .findBy(attribute("data-day", String.valueOf(meetingTimestamp)))
                .click();

        $("[data-test-id=date] input.input__control")
                .shouldHave(value(meetingDate.format(
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"))));

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
        // Проверяем отображение элемента загрузки
        $(".button__icon .spin").should(Condition.visible);

        // Проверяем отображение уведомления
        $("[data-test-id=notification]")
                .should(Condition.visible, Duration.ofSeconds(15));

// Проверяем завершение загрузки не более чем за 15 секунд
        $(".button__icon .spin")
                .should(Condition.disappear, Duration.ofSeconds(15));

// Проверяем заголовок уведомления
        $(".notification__title").should(Condition.text("Успешно!"));

// Проверяем текст уведомления
        $(".notification__content").should(Condition.text(
                "Встреча успешно забронирована на " +
                        meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));

        // Проверяем отображение кнопки закрытия уведомления
        $(".notification__closer").should(Condition.visible);

// Проверяем отображение иконки крестика
        $(".notification__closer .icon_name_close").should(Condition.visible);

        // Нажимаем на крестик
        $(".notification__closer").click();

// Проверяем, что уведомление закрылось
        $("[data-test-id=notification]").should(Condition.not(Condition.visible));


    }

}
