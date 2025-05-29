package steps;

import model.AuthResponseModel;
import model.UserRequestBodyModel;

import static io.restassured.RestAssured.given;
import static spec.BaseSpec.baseResponseSpec;
import static spec.BaseSpec.requestBaseSpec;

public class LoginApiSteps {
    public AuthResponseModel loginApiCall(UserRequestBodyModel request){
        return given(requestBaseSpec)
            .body(request)
            .when()
            .post("/Account/v1/Login")
            .then()
            .spec(baseResponseSpec(200))
            .extract().as(AuthResponseModel.class);
    }
}
