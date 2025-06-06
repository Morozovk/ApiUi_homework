package tests.api_ui;

import com.codeborne.selenide.Selenide;
import helpers.WithLogin;

import model.AuthResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.BookApiSteps;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.*;

public class UserApiUi extends TestBase {


    @WithLogin
    @Test
    @Tag("Smoke")
    void login() {
        open("/profile");
        $("#userName-value").shouldHave(visible);
    }

    @WithLogin
    @Test
    void deleteBook() {

        String token = getWebDriver().manage().getCookieNamed("token").getValue();
        String userId = getWebDriver().manage().getCookieNamed("userID").getValue();


        BookApiSteps.deleteBooks(token, userId);
        BookApiSteps.addBook(token, userId, isbn);

        open("/profile");
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
        sleep(3000);
        Selenide.refresh();
        $(".rt-noData").shouldHave(text("No rows found"));
    }
}