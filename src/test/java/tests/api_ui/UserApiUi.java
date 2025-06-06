package tests.api_ui;

import helpers.WithLogin;

import model.AuthResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.BookApiSteps;
import tests.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
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

        AuthResponseModel auth = new AuthResponseModel();
        String token = auth.getToken();
        String userId = auth.getUserId();

        BookApiSteps.deleteBook(token, userId);
        BookApiSteps.addBook(token, userId, isbn);

        open("/profile");
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
    }
}