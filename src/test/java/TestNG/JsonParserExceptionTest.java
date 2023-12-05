package TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;

import java.io.File;

public class JsonParserExceptionTest {

    @DataProvider(name = "fileExceptionDataProvider")
    public Object[][] createFileExceptionTestData() {
        return new Object[][] {
                {"nonexistent.json"}, // Non-existent file
                {"invalid/directory/path"}, // File path pointing to a directory
                {"invalidJsonFormat.json"}, // File with invalid JSON format
                {"emptyFile.json"}, // Empty file
                {"noReadPermission.json"} // File exists but no read permission
        };
    }

    @Test(dataProvider = "fileExceptionDataProvider", expectedExceptions = NoSuchFileException.class)
    public void testReadFromFileExceptionHandling(String filename) {
        JsonParser parser = new JsonParser();
        File file = new File("src/main/resources/" + filename);
        parser.readFromFile(file);
    }
}

