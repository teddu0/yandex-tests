package ui.yandex.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FolderManipulationTests extends TestBase {

    @Test
    public void deleteCopiedFileFromFolderTest() throws InterruptedException {
        goToAuthorizationPage();
        authorizationByLogin("simtestmail", "Simtest");
        goToYandexDrivePage();
        copyFolder();
        openSomeObject(By.xpath("//div[@aria-label='Просто папка']"));
        deleteSomeFile();
        copiedFileIsPresent("Файл для копирования.jpg");
    }

    @Test //необязательно задание - задание со звездочкой
    public void createFolderAndUploadFileTest() throws InterruptedException {
        goToAuthorizationPage();
        authorizationByLogin("simtestmail", "Simtest");
        goToYandexDrivePage();
        createFolder("Hello, SimbirSoft");
        openSomeObject(By.xpath("//div[@aria-label='Hello, SimbirSoft']"));
        uploadSomeFile();
        Thread.sleep(3000);
        openSomeObject(By.xpath("//div[@aria-label='hello.txt']"));
        checkingFileText("hello");
    }



}
