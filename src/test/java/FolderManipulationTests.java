import org.testng.annotations.Test;

public class FolderManipulationTests extends TestBase {

    @Test
    public void deleteCopiedFileFromFolder() throws InterruptedException {
        goToAuthorizationPage();
        authorizationByLogin("simtestmail", "Simtest");
        Thread.sleep(3000); // временныый слип для проверки результата

    }
}
