package steps;

import model.AddBookRequestModel;
import model.AddBookResponseModel;
import model.AuthResponseModel;
import model.UserRequestBodyModel;

import static io.restassured.RestAssured.given;

import static spec.BaseSpec.baseResponseSpec;

import static spec.BaseSpec.requestBaseSpec;

public class AddBookSteps {
    public static AddBookResponseModel addBookCall(String token, String userId, String isbn) {
        String jsonBody = String.format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, isbn);

       return given(requestBaseSpec)
                .header("Authorization", "Bearer " + token)
                .body(jsonBody)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(baseResponseSpec(201))
                .extract().as(AddBookResponseModel.class);
    }
}
