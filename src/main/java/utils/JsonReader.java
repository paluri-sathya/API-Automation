package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;

public class JsonReader {

    public static String getTestData(String key) throws  Exception {
        String testdata;
        return testdata = (String) getJsonData().get(key);
    }

    public static JSONObject getJsonData() throws Exception {

        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + workingDir);

        // Build full path to file
        // Build full path to file
        String filePath = workingDir + File.separator + "resources" + File.separator + "TestData" + File.separator + "Testdata.json";
        System.out.println("Current working directory: " + filePath);



        //pass the path of the testdata.json file
        File filename = new File(filePath);
        // Print info
        System.out.println("Checking file at: " + filename.getAbsolutePath());
        //convert json file into string
        String json = FileUtils.readFileToString(filename,"UTF-8");
        //parse the string into object
        Object obj = new JSONParser().parse(json);
        //give jsonobject so that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
}
