import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDelivery {
    public static String setLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }


    @Test
    void shouldFillinApplication() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Прекрасная Елена");
        $("[data-test-id=phone] input").setValue("+79876754534");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldHyphenCity() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Прекрасная Елена");
        $("[data-test-id=phone] input").setValue("+79876754534");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMoreThreeDays() {
        String date = setLocalDate(5);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Уважаемый Человек");
        $("[data-test-id=phone] input").setValue("+79873451234");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldResultDoubleSurname() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Тверь");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Боко-Косов Косогор");
        $("[data-test-id=phone] input").setValue("+79013456543");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldHighlightDate() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        //$(byText("Выберите дату встречи с представителем банка")).click();
        LocalDate defaultDay = LocalDate.now().plusDays(3);
        LocalDate planDay = LocalDate.now().plusDays(7);
        String date = setLocalDate(7);
        $("[data-test-id=date] [value]").click();
        if ((planDay.getYear() > defaultDay.getYear() | planDay.getMonthValue() > defaultDay.getMonthValue())) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }
        String searchingDay = String.valueOf(planDay.getDayOfMonth());
        $$("td.calendar__day").find(text(searchingDay)).click();
        $("[data-test-id=name] input").setValue("Прекрасная Елена");
        $("[data-test-id=phone] input").setValue("+79876754534");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldHighlightCity() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Самара")).click();
        //$(byText("Выберите дату встречи с представителем банка")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Шлепкин Василий");
        $("[data-test-id=phone] input").setValue("+79876754534");
        //$x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }
}