package tests.api_ui;

import helpers.WithLogin;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.LoginApiSteps;
import tests.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UserApiUi extends TestBase {
LoginApiSteps loginApiSteps = new LoginApiSteps();


    @WithLogin
    @Test
    @Tag("Smoke")
    void login() {
        open("/profile");
        $("#userName-value").shouldHave(visible);
    }
}