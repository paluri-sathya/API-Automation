package userManagement;
import core.HttpstatusCode;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;
import utils.PropertyReader;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class getusers {

    @Test (groups = "SmokeSuite")
    public void getUserData() {
        given().
                when().get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200);
    }

    @Test(groups ="RegressionSuite")
    public void validateResponseBody() {
        given().
                when()
                .get("https://jsonplaceholder.typicode.com/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("userId", equalTo(1));
    }

    @Test
    public void validateResponsehasItems() {
        Response response =
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/posts")
                        .then()
                        .extract()
                        .response();
        assertThat(response.jsonPath().getList("title"), hasItems("qui est esse", "eum et est occaecati"));

    }

    @Test
    public void validateResponsehasSize() {
        Response response =
                given()
                        .when().get("https://jsonplaceholder.typicode.com/comments")
                        .then()
                        .extract()
                        .response();
        assertThat(response.jsonPath().getList(""), hasSize(500));
    }

    @Test
    public void validateResponseContains() {
        Response response =
                given()
                        .when().get("https://jsonplaceholder.typicode.com/comments?postId=1")
                        .then()
                        .extract()
                        .response();
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz", "Lew@alysha.tv", "Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
    }

    @Test(groups ="RegressionSuite")
    public void validateResponseIS() {
        Response response = given()
                .queryParam("page", 2)
                .when().get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
        //response.then().body("data",hasSize(5));
        response.then().body("data[0].id", is(7));
        response.then().body("data[0].email", is("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name", is("Michael"));
        response.then().body("data[0].last_name", is("Lawson"));
        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));
    }

    @Test
    public void validateStatusCodeGetUser() {
        Response resp =
                given()
                        .queryParam("page", 2)
                        .when()
                        .get("https://reqres.in/api/users");
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
    }

    @Test
    public void validatemultiplequeryparam() {
        Response resp = given()
                .queryParam("page", 2)
                .queryParam("per-page", 6)
                .when().get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
    }
    @Test
    public void testCreateUserWithFormParam() {
        Response resp =
                given()
                        .contentType("application/x-www-form-utlencoded")
                        .formParam("name", "Jhon Doe")
                        .formParam("job", "Developer")
                        .when()
                        .get("https://reqres.in/api/users")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
        resp.then().body("name", equalTo("Jhon Doe"));
        resp.then().body("job", equalTo("Developer"));
    }

    @Test
    public void TestGetUserWithHeader() {
        given()
                .header("content-type", "application/json")
                .header("connection", "keep-alive")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("testTestGetUserWithHeader executed succesfully");
    }

    @Test
    public void TestHeadersUsingMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("connection", "keep-alive");

        given()
                .headers(headers)
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("TestHeadersUsingMap executed Succesfully");
    }

    @Test
    public void TestFetchHeaders() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();
        Headers headers = response.getHeaders();
        for (Header h : headers) {
            if (h.getName().contains("connection")) {
                assertEquals(h.getValue(), "keep-alive");
            }
            //System.out.println(h.getName() + ":" + h.getValue());
        }
    }

    @Test
    public void TestUseCookies() {
        given()
                .cookie("test", "testing")
                .cookie("testi1", "testing2")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void TestFetchCookies() {
        Response response = given()
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();
        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, 200);
    }
    @Test
    public void TestCodeDelete() {
        Response resp = given()
                .headers("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/2");
        assertEquals(resp.getStatusCode(), 204);
        System.out.println("**********************");
    }

    @Test
    public void TestValidateJsonReader() throws Exception {
        String username = JsonReader.getTestData("Username");
        String password = JsonReader.getTestData("password");
        System.out.println(username + " : " + password);
        Response resp = given()
                .auth()
                .basic(username, password)
                .when()
                .get("https://postman-echo.com/basic-auth");
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println("TestValidateJsonReader executed succesfully");
    }
    @Test
    public void validatepropertiesandTestdata() throws Exception {
        String serverAddress = PropertyReader.PropertyReader("config.Properties", "server");
        String endpoint = JsonReader.getTestData("endpoint");
        String URL = serverAddress + endpoint;
        System.out.println("URL " + URL);
        Response resp = given()
                        .queryParam("page", 2)
                        .when()
                        .get(URL);
        int actualStatusCode = resp.statusCode();
        assertEquals(actualStatusCode, 200);
    }
    @Test
    public void hardassert(){
        System.out.println("hardassert");
        Assert.assertTrue(false);
        Assert.assertTrue(false);
        Assert.assertTrue(false);
        Assert.assertTrue(false);
    }
    @Test
    public void softassert(){
        SoftAssert softAssertion = new SoftAssert();
        System.out.println("softassert");
        softAssertion.assertTrue(false);
        softAssertion.assertTrue(true);
        //softAssertion.assertAll();
    }
    @DataProvider(name = "testdata")
    public Object[][] testData(){
        return new Object[][]{
                {"1", "jhon"},
                {"2","jane"},
                {"3","Bob"}
        };
    }
    @Test(dataProvider = "testdata")
    @Parameters({"id","name"})
    public void testEndpoint(String id, String name) {
        given()
                .queryParam("id", id)
                .queryParam("name",name)
                .when()
                .get("https://reqres.in/api/users");
    }
    @Test
    public void validatpostwithstring(){
        Response response = given()
                .header("x-api-key","reqres-free-v1")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(),HttpstatusCode.Created.code);
        System.out.println("validatpostwithstring executed succesfully");
        System.out.println(response.getBody().asString());
    }
    @Test
    public void jsonSchemaValidation() {
        File schema = new File("resources/ExpectedSchema.json");
        given().
                when().get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}

