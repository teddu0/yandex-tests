package api.yandex.tests;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FolderCreationTests extends TestBase{
    @Test
    public void testCreationFolder() {
        createNewFolder();
        getCreatedFolder();
    }

    @AfterTest
    public void postCondition() {
        deleteCreatedFolder();
    }


}
