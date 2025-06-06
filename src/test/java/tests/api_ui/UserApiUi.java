package tests.api_ui;

import helpers.WithLogin;

import io.restassured.response.Response;
import model.AuthResponseModel;
import model.UserRequestBodyModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.LoginApiSteps;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static tests.TestData.login;
import static tests.TestData.password;

public class UserApiUi extends TestBase {


    @WithLogin
    @Test
    @Tag("Smoke")
    void login() {
        open("/profile");
        $("#userName-value").shouldHave(visible);
    }



}