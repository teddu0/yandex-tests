package ui.yandex.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.BACK_SPACE;

public class TestBase {

    public WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru/");
    }

    @AfterTest
    public void stop() {
        driver.findElement(By.xpath("//img[@class='user-pic__image']")).click();
        driver.findElement(By.xpath("//a[@aria-label='Выйти из аккаунта']")).click();
        driver.close();
        driver.quit();
    }

    public void authorizationByLogin(String login, String password) {
        driver.findElement(By.xpath("//button[@data-type='login']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(login);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("passp-field-passwd")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void goToAuthorizationPage() {
        driver.findElement(By.xpath("//div[@class='desk-notif-card__login-new-item-title']")).click();
    }

    public void goToYandexDrivePage() {
        driver.findElement(By.xpath("//a[@href='https://disk.yandex.ru/?source=domik-main']")).click();
    }

    public void copyFolder() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        driver.findElement(By.xpath("//div[@aria-label='Файл для копирования.jpg']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Копировать']")).click();
        driver.findElement(By.xpath("//div[@title='Просто папка']")).click();
        driver.findElement(By.cssSelector(".confirmation-dialog__button_submit")).click();
    }

    public void openSomeObject(By locator) {
        Actions actions = new Actions(driver);
        WebElement elementLocator = driver.findElement(locator);
        actions.doubleClick(elementLocator).perform();
    }

    public void deleteSomeFile() {
        driver.findElement(By.xpath("//div[@aria-label='Новый документ.docx']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Удалить']")).click();
    }

    public void copiedFileIsPresent(String fileTitle) {
        WebElement getTitle = driver.findElement(By.xpath("//div[@aria-label='Файл для копирования.jpg']"));
        getTitle.getAttribute("aria-label");
        Assert.assertEquals(getTitle.getAttribute("aria-label"), fileTitle);
    }

    public void createFolder(String nameOfFolder) {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        driver.findElement(By.xpath("//span[@class='create-resource-popup-with-anchor']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Папку']")).click();
        WebElement fillInput = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[3]/div[2]/div/div/div/div/div/div[1]/div/form/span/input"));
        while(fillInput.getAttribute("value") != "") {
            fillInput.sendKeys(Keys.BACK_SPACE);
        }
        fillInput.sendKeys(nameOfFolder);
        fillInput.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//button[@aria-label='Отменить выделение']")).click();
    }
    public void uploadSomeFile() {
        By fileInput = (By.xpath("//input[@type='file']"));
        String filePath = "C:\\hello.txt"; // перед прогоном вам нужно создать файл hello.txt c текстом "hello" внутри и переместить его на диск C:
        driver.findElement(fileInput).sendKeys(filePath);
    }

    public void checkingFileText(String textIfFile) {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        WebElement textValue = driver.findElement(By.xpath("//p[@class='mg1']"));
        textValue.getText();
        Assert.assertEquals(textValue.getText(), textIfFile);
        driver.switchTo().window(tabs2.get(0));
        driver.findElement(By.xpath("//button[@aria-label='Отменить выделение']")).click();
    }
}

/*
Надеюсь вы дошли до этого комментария :)
Хочу добавить следующее:
1. Здесь можно было разгрузить базовый класс (TestBase) путем выделения методов в отдельные классы-помощники, но я не стал этого делать из-за пары тестов;
2. Можно было обеспечить тесты выполением предусловий (мы же не сможем удалить папку, которой нет в папке). */
