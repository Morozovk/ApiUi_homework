package tests.api_ui;

import helpers.WithLogin;

import model.AuthResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.AddBookSteps;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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
    @Tag("Smoke")
    void addBook() {
        AuthResponseModel auth = new AuthResponseModel();
        String token = auth.getToken();
        String userId = auth.getUserId();
        String isbn = "9781449365035";

        AddBookSteps.addBookCall(token, userId, isbn);
        open("/profile");
        $(".ReactTable").shouldHave(text("Speaking JavaScript"));
    }
}