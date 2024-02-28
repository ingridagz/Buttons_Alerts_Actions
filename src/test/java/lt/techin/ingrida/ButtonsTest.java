package lt.techin.ingrida;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ButtonsTest {

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
        driver.get("https://webdriveruniversity.com/Click-Buttons/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    //1. WebElement Click
//    Paspausti Mygtuka 1 naudojant WebElement click() metoda.
//    patikrinti ar atsiranda popup pranesimas su tinkama sekmes zinute.
//    uzdaryti popup langa.
    @Test
    void webElementClick() {
        WebElement webElButton = driver.findElement(new By.ById("button1"));
        webElButton.click();
        palaukti();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement popupWindow = driver.findElement(By.xpath("//*[@id=\"myModalClick\"]/div/div/div[2]/p"));

        wait.until(ExpectedConditions.visibilityOf(popupWindow));
        System.out.println(popupWindow.getText());
        palaukti();
        Assertions.assertEquals("Well done for successfully using the click() method!", popupWindow.getText());
        palaukti();
        WebElement popupButton = driver.findElement(By.xpath("//*[@id=\"myModalClick\"]/div/div/div[3]/button"));
        popupButton.click();
        palaukti();
    }
    //    2.JavaScript Click
//     Paspausti Mygtuka 2 naudojant JavaScript click
//     Patikrinti ar atsiranda popup pranesimas su tinkama zinute.
//    uzdaryti popup langa

    @Test
    void javaScriptClick() {
        WebElement javaScrButton = driver.findElement(new By.ById("button2"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", javaScrButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement popupWindow2 = driver.findElement(By.xpath("//*[@id=\"myModalJSClick\"]/div/div/div[1]/h4"));
        wait.until((ExpectedConditions.visibilityOf(popupWindow2)));

        Assertions.assertEquals("It’s that Easy!! Well I think it is.....", popupWindow2.getText());

        WebElement popupButton2 = driver.findElement(By.xpath("//*[@id=\"myModalJSClick\"]/div/div/div[3]/button"));
        js.executeScript("arguments[0].click();", popupButton2);
    }
//    3. Sukurti WebElement objekta Mygtukui 3 naudojant pasirinkta lokatoriu
    // Paspausti mygtuka 3 naudojant Action Move $ Click Metoda
    // Patikrinkite ar atsiranda popup pranesimas su tinkama sekmes zinute

    @Test
    void actionMoveAndClick() {
        Actions actions = new Actions(driver);
        WebElement actMoveButton = driver.findElement(new By.ById("button3"));
        actions.click(actMoveButton).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement popupWindow3 = driver.findElement(By.xpath("//*[@id=\"myModalMoveClick\"]/div/div/div[1]/h4"));
        wait.until(ExpectedConditions.visibilityOf(popupWindow3));

        Assertions.assertEquals("Well done! the Action Move & Click can become very useful!", popupWindow3.getText());
    }
    @AfterEach
    void afterEach() {
        driver.quit();
    }
}
