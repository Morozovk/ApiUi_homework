package steps;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static spec.BaseSpec.baseResponseSpec;
import static spec.BaseSpec.requestBaseSpec;

public class BookApiSteps {

    public static void deleteBooks(String token, String userId){
        given(requestBaseSpec)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(baseResponseSpec(204));
    }

    public static void addBook(String token, String userId, String isbn) {
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, isbn);

        given(requestBaseSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(baseResponseSpec(201));
    }
}
