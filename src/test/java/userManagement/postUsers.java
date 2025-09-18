package userManagement;

import core.HttpstatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class postUsers {

    private static FileInputStream FileInputStreamMethod(String requestBodyFileName) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(
                    new File(System.getProperty("user.dir") + "/resources/Testdata/" + requestBodyFileName));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }

    @Test
    public void validatpostwithstring() {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatpostwithstring executed succesfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatepostwithjsonfile() throws IOException {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(IOUtils.toString(FileInputStreamMethod("PostRequestBody.json")))
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatpostwithjsonfile executed succesfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatepostwithpojo() {
        postRequestBody postrequest = new postRequestBody();
        postrequest.setJob("leader");
        postrequest.setName("morpheus");

        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(postrequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatepostwithpojo executed succesfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatepostwithpojolist() {
        List<String> Languagelist = new ArrayList<>();
        Languagelist.add("Java");
        Languagelist.add("Python");
        postRequestBody postrequest = new postRequestBody();
        postrequest.setJob("leader");
        postrequest.setName("morpheus");
        postrequest.setLanguages(Languagelist);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(postrequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatepostwithpojolist executed succesfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validateputwithpojo() {
        postRequestBody putrequest = new postRequestBody();
        putrequest.setJob("Analyst");
        putrequest.setName("Sathya");
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(putrequest)
                .when()
                .post("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validateputwithpojo executed succesfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatepatchwithpojo() {
        postRequestBody patchrequest = new postRequestBody();
        patchrequest.setName("morpheus");
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(patchrequest)
                .when()
                .post("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatepatchwithpojo executed succesfully");
        System.out.println(response.getBody().asString());

    }

    @Test
    public void validatepostwithpojocityobject() {
        List<String> Languagelist = new ArrayList<>();
        Languagelist.add("Java");
        Languagelist.add("Python");

        List<cityRequest> cityRequest = new ArrayList<>();
        cityRequest cityRequest1 = null;
        cityRequest.add(cityRequest1);
        cityRequest cityRequest2 = null;
        cityRequest.add(cityRequest2);

        cityRequest1 = new cityRequest();
        cityRequest1.setName("banglore");
        cityRequest1.setTemperature("30");

        cityRequest2 = new cityRequest();
        cityRequest2.setName("delhi");
        cityRequest2.setTemperature("40");

        postRequestBody postrequest = new postRequestBody();
        postrequest.setJob("leader");
        postrequest.setName("morpheus");
        postrequest.setLanguages(Languagelist);
        postrequest.setCityRequestBody(cityRequest);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(postrequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatepostwithpojocityobject executed succesfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatepatchwithpojorespbody() {
        String job = "morpheus";
        postRequestBody patchrequest = new postRequestBody();
        patchrequest.setName(job);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(patchrequest)
                .when()
                .post("https://reqres.in/api/users/2");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getJob());
        assertEquals(responseBody.getJob(), job);
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatepatchwithpojo executed succesfully");
        System.out.println(response.getBody().asString());

    }
    @Test
    public void validatepostwithresponsepojocityobject() {
        List<String> Languagelist = new ArrayList<String>();
        Languagelist.add("Java");
        Languagelist.add("Python");

        List<cityRequest> cityRequest = new ArrayList<>();
        cityRequest cityRequest1 = null;
        cityRequest.add(cityRequest1);
        cityRequest cityRequest2 = null;
        cityRequest.add(cityRequest2);

        cityRequest1 = new cityRequest();
        cityRequest1.setName("banglore");
        cityRequest1.setTemperature("30");

        cityRequest2 = new cityRequest();
        cityRequest2.setName("delhi");
        cityRequest2.setTemperature("40");

        postRequestBody postrequest = new postRequestBody();
        postrequest.setJob("leader");
        postrequest.setName("morpheus");
        postrequest.setLanguages(Languagelist);
        postrequest.setCityRequestBody(cityRequest);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(postrequest)
                .when()
                .post("https://reqres.in/api/users");
        postRequestBody responseBody = response.as(postRequestBody.class);
        responseBody.getCityRequestBody();
        responseBody.getLanguages();
        assertEquals(response.getStatusCode(), HttpstatusCode.Created.code);
        System.out.println("validatepostwithpojocityobject executed succesfully");
        System.out.println(response.getBody().asString());
    }


}
