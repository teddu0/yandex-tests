package ui.yandex.tests;

import org.testng.annotations.Test;

public class FolderManipulationTests extends TestBase {

    @Test
    public void deleteCopiedFileFromFolder() throws InterruptedException {
        goToAuthorizationPage();
        authorizationByLogin("simtestmail", "Simtest");
        goToYandexDrivePage();
        copyFolder();
        openFolder();
        deleteSomeFile();
        copiedFileIsPresent("Файл для копирования.jpg");
    }


}
