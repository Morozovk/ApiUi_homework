package tests.api;

import model.UserRequestBodyModel;
import model.CreateUserResponseBodyModel;
import model.ErrorCreateUserResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static spec.BaseSpec.baseResponseSpec;
import static spec.BaseSpec.requestBaseSpec;


public class UserApi extends TestBase {

    @Test
    @Tag("Smoke")
    void createUser() {
        UserRequestBodyModel authData = new UserRequestBodyModel("Kirill058", "12345@Kk");

        CreateUserResponseBodyModel response = step("Отправялем запрос", () ->
                given(requestBaseSpec)
                        .body(authData)

                        .when()
                        .post("Account/v1/User")
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(CreateUserResponseBodyModel.class));

        step("Проверяем ответ", () -> {
            assertNotNull(response.getUserID());
            assertNotNull(response.getUsername());
            assertNotNull(response.getBooks());
        });
    }


    @Test
    @Tag("Smoke")
    void existsUser() {
        UserRequestBodyModel authData = new UserRequestBodyModel("test", "12345@Kk");

        ErrorCreateUserResponseModel response = step("Отправялем запрос", () ->
                given(requestBaseSpec)
                        .body(authData)

                        .when()
                        .post("Account/v1/User")
                        .then()
                        .spec(baseResponseSpec(406))
                        .extract().as(ErrorCreateUserResponseModel.class));

        step("Проверяем ответ", () -> {
            assertEquals("1204", response.getCode());
            assertEquals("User exists!", response.getMessage());
        });
    }
}


