package helpers;

import io.qameta.allure.Step;
import model.AuthResponseModel;
import model.UserRequestBodyModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import steps.LoginApiSteps;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Step("Добавляем в Cookie данные для авторизации")
    @Override
    public void beforeEach(ExtensionContext context) {
        UserRequestBodyModel authData = new UserRequestBodyModel("Kirill058", "12345@Kk");

        LoginApiSteps loginApiSteps = new LoginApiSteps();
        AuthResponseModel response = loginApiSteps.loginApiCall(authData);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
    }
}