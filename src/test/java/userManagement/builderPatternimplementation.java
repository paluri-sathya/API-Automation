package userManagement;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class builderPatternimplementation {
    @Test
    public void testRestAssuredFormalApproach(){
        given()
                .contentType("application/x-www-form-utlencoded")
                .queryParam("userId",1)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .assertThat()
                .statusCode(200);
    }

}