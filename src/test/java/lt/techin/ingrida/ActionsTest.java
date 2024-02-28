package lt.techin.ingrida;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionsTest {

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
        driver.get("https://webdriveruniversity.com/Actions/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    //1.Drag and Drop
//Tempkite dezute "Draggable" i dezute "Droppable".
//Petikrinkite ar "Droppable" dezute pakeite spalva ir teksta.
    @Test
    void dragDrop() {
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        System.out.println("Result: Text before is: " + droppable.getText());
//       Elements->Computed-> "background-color" -> rgb (97,109,179,1)
        System.out.println("Result: Background color before is blue! " + droppable.getCssValue("background-color"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).perform();

        Assertions.assertEquals("Dropped!", droppable.getText());
        System.out.println("Result: Text after is: " + droppable.getText());

        Assertions.assertNotEquals(droppable, droppable.getCssValue("background-color"));
//       Elements->Computed-> "background-color" -> rgb (244,89,80,1)
        System.out.println("Result: Background color after is red! " + droppable.getCssValue("background-color"));
    }

    //2.
//Atlikite dviguba spustelejima sekcijai "Double Click Mel".
    @Test
    void doubleclick() {
        WebElement boxBackgroundColor = driver.findElement(new By.ByCssSelector("div#double-click"));
        System.out.println("Result: Background color before is yellow! " + boxBackgroundColor.getCssValue("background-color"));

        Actions actions = new Actions(driver);
        actions.doubleClick(boxBackgroundColor).perform();

        Assertions.assertNotEquals("rgba(254, 196, 45, 1)", boxBackgroundColor.getCssValue("background-color"));
        System.out.println("Result: Background color after is green! " + boxBackgroundColor.getCssValue("background-color"));
    }

    //3.Peles uzvedimas "Hover"
//Atlikite peles uzvedimo veiksmus ant "Hover over me" elementu.
    @Test
    void hover() {
        WebElement hoverFirst = this.driver.findElement(By.xpath("//div[@id='div-hover']/div[1]"));

        Actions actions = new Actions(this.driver);
        actions.moveToElement(hoverFirst).perform();
        Assertions.assertTrue(hoverFirst.isDisplayed());
        System.out.println(hoverFirst.isDisplayed());

        WebElement hoverTwo = this.driver.findElement(By.xpath("//div[@id='div-hover']/div[2]"));
        actions.moveToElement(hoverTwo).perform();
        Assertions.assertTrue(hoverTwo.isDisplayed());

        WebElement hoverThree = this.driver.findElement(By.xpath("//div[@id='div-hover']/div[3]"));
        actions.moveToElement(hoverThree).perform();
        Assertions.assertTrue(hoverThree.isDisplayed());
    }

    //4.Paspauskite ir laikykite
//Paspauskite ir laikykite dezute "Click and hold".
//Patikrinkite ar dezute pakeite savo spalva ir teksta.
    @Test
    void clickHold() {
        WebElement button = driver.findElement(By.id("click-box"));
//        black=(0,0,0,1); red=rgb(255, 99, 71);  green=rgb(0, 255, 0)
        System.out.println("Result: Is black before?: "+ button.getCssValue("background-color"));
        System.out.println("Result: Text is 'Click and Hold!' before?: "+ button.getText());

        Actions actions = new Actions(driver);
        actions.clickAndHold(button).perform();

        Assertions.assertTrue(button.getAttribute("style").contains("background: rgb(0, 255, 0)"));
        System.out.println("Result: Is green after?: "+ button.getAttribute("style").contains("background: rgb(0, 255, 0)"));

        Assertions.assertEquals("Well done! keep holding that click now.....", button.getText());
        System.out.println("Result: Text is: 'Well done! keep holding that click now.....' after? "+ button.getText());
    }
    @AfterEach
    void afterEach() {
        driver.quit();
    }
}
