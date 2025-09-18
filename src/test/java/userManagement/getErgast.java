package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class getErgast {

    @Test
    public void validateResponseBodyGetPathParam() {
        Response resp =
                given()
                        .pathParam("raceSeason", 2017)
                        .when()
                        .get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println(resp.body().asString());
    }
}
