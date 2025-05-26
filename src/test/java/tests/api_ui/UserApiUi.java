package tests.api_ui;
import io.restassured.http.Cookie;
import model.AuthResponseModel;
import model.UserRequestBodyModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static spec.BaseSpec.baseResponseSpec;
import static spec.BaseSpec.requestBaseSpec;

public class UserApiUi extends TestBase {

    @Test
    @Tag("Smoke")
    void login() {
        UserRequestBodyModel authData = new UserRequestBodyModel("KirillMorozov", "12345678@Kk");

        AuthResponseModel response = new AuthResponseModel();
                given(requestBaseSpec)
                .body(authData)

                .when()
                .post("Account/v1/Authorized")
                .then()
                .spec(baseResponseSpec(200))
                .extract().response();

        open("/images/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));


        open("/login");
        $("#userName-value").shouldHave(visible);
    }
}
