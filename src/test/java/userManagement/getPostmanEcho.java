package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class getPostmanEcho {
    @Test
    public void validatepropertyfile() {
        String serverAddress = PropertyReader.PropertyReader("config.Properties", "serverAddress");
        System.out.println("Server Address is : " + serverAddress);
        Response resp =
                given()
                        .queryParam("page", 2)
                        .when()
                        .get(serverAddress);
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println("validatepropertyfile executed successfully");
    }
    @Test
    public void TestDigestAuth() {
        Response resp = given()
                //.headers("Host","<calculated when request is sent>")
                .auth()
                .digest("postman", "password")
                .when()
                .get("https://postman-echo.com/digest-auth");
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println(resp.body().asString());
    }
    @Test(groups ="RegressionSuite")
    public void TestGetBasicAuth() {
        Response resp = given()
                .auth()
                .basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth");
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println(resp.body().asString());
    }
}
