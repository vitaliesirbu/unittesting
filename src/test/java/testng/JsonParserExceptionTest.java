package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;

import java.io.File;

public class JsonParserExceptionTest {

    @DataProvider(name = "fileExceptionDataProvider")
    public Object[][] createFileExceptionTestData() {
        return new Object[][] {
                {"nonexistent.json"},
                {"invalid/directory/path"},
                {"invalidJsonFormat.json"},
                {"emptyFile.json"},
                {"noReadPermission.json"}
        };
    }

    @Test(dataProvider = "fileExceptionDataProvider", expectedExceptions = NoSuchFileException.class)
    public void testReadFromFileExceptionHandling(String filename) {
        JsonParser parser = new JsonParser();
        File file = new File("src/main/resources/" + filename);
        parser.readFromFile(file);
    }
}

