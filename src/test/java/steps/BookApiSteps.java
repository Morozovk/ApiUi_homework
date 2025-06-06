package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.AuthResponseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static spec.BaseSpec.baseResponseSpec;
import static spec.BaseSpec.requestBaseSpec;
import static tests.TestData.login;
import static tests.TestData.password;

public class BookApiSteps {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    void addBookToCollectionTest() {

        String authData = "{\"userName\":\"" + login + "\",\"password\":\"" + password + "\"}";

        AuthResponseModel authResponse = given(requestBaseSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(baseResponseSpec(200))
                .extract().as(AuthResponseModel.class);

        given(requestBaseSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .queryParams("UserId", authResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(baseResponseSpec(204));


        String isbn = "9781449365035";
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.getUserId() , isbn);

        given(requestBaseSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(baseResponseSpec(201));
    }
}
