package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class UploadDownload {

    @Test
    public void FileUploadExample(){
        File file = new File("resources\\TestData\\Demo.txt");
        Response response = given()
                .multiPart("file", file)
                .when()
                .post("https://example.com/upload");
        System.out.println(response.getStatusCode());
    }

}
