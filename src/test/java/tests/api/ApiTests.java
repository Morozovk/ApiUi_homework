package tests.api;

import helpers.WithLogin;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static spec.BaseSpec.baseResponseSpec;
import static spec.BaseSpec.requestBaseSpec;
import static tests.TestData.login;
import static tests.TestData.password;


public class ApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI ="https://demoqa.com";
    }

    @Test
    @Tag("Smoke")
    void createUserTest() {
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
    void existsUserTest() {
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

    @Test
    @Tag("Smoke")
    void notCorrectPasswordTest() {
        UserRequestBodyModel authData = new UserRequestBodyModel("Kirill058", "12345@");

        ErrorCreateUserResponseModel response = step("Отправялем запрос", () ->
                given(requestBaseSpec)
                        .body(authData)

                        .when()
                        .post("Account/v1/User")
                        .then()
                        .spec(baseResponseSpec(400))
                        .extract().as(ErrorCreateUserResponseModel.class));

        step("Проверяем ответ", () -> {
            assertEquals("1300", response.getCode());
            assertEquals("Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), " +
                    "one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be " +
                    "eight characters or longer.", response.getMessage());
        });
    }

    @Test
    @Tag("Smoke")
    void getListBookStoreTest(){

        ListBookResponse response = step("Отправляем запрос", () ->
                given(requestBaseSpec)
                        .when()
                        .get("BookStore/v1/Books")
                        .then()
                        .spec(baseResponseSpec(200))
                        .extract().as(ListBookResponse.class));
    }

    @Test
    @Tag("Smoke")
    void addBookTest(){
        ListCollectionOfIsbns isbn = new ListCollectionOfIsbns();
        isbn.setIsbn("9781449325862");

        AddListOfBooksRequestModel requestBody = new AddListOfBooksRequestModel ();
        requestBody.setUserId("9d6383a5-5a72-409d-8bdc-bbd8be0c163d");
        requestBody.setListCollectionOfIsbns(Arrays.asList(isbn));

        AddBookResponseModel response = step("Отправляем запрос", () ->
                given(requestBaseSpec)
                        .when()
                        .body(requestBody)
                        .post("BookStore/v1/Books")
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(AddBookResponseModel.class));
    }


}


