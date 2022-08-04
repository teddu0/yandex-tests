package ui.yandex.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

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
}
