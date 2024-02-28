package lt.techin.ingrida;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertTest {
    WebDriver driver;

    public static void palaukti() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.get("https://webdriveruniversity.com/Popup-Alerts/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    //1.Patikrinti "JavaScript Alert"
    @Test
    void alert() {
        WebElement button = driver.findElement(By.id("button1"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assertions.assertEquals("I am an alert box!", alert.getText());
        alert.accept();
    }

    //2.Patikrinti "JavaScript Confirm Box"
    @Test
    void confirmBox() {
        WebElement button = driver.findElement(By.id("button4"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assertions.assertEquals("Press a button!", alert.getText());
        alert.accept();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("confirm-alert-text"), "You pressed OK!"));

        String actualText = driver.findElement(By.id("confirm-alert-text")).getText();
        Assertions.assertEquals("You pressed OK!", actualText);
    }

    //3.Patikrinti "Modal Popup"
    @Test
    void modalPopup() {
        WebElement button = driver.findElement(By.id("button2"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-title")));

        String expectedText = "It’s that Easy!! Well I think it is.....";
        Assertions.assertEquals(expectedText, modalWindow.getText());

        WebElement closeButton = driver.findElement(By.xpath("//div[@class='modal-footer']/button[@type='button']"));
        closeButton.click();
    }

    //4.Patikrinti "Ajax loader"
    @Test
    void ajaxLoader() {
        WebElement buttonBut = driver.findElement(By.id("button3"));
        buttonBut.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dynamicButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button1")));
        dynamicButton.click();

        WebElement modalHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-title")));
        String expectedHeaderText = "Well Done For Waiting....!!!";

        Assertions.assertEquals(expectedHeaderText, modalHeader.getText());

        WebElement closeButton = driver.findElement(By.cssSelector(".modal-footer [data-dismiss]"));
        closeButton.click();
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }
}
