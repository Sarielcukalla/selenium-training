package rahulshettyacademy.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //url
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingpage= new LandingPage(driver);
        //Log in
        String email = "cukallasariel@gmail.com";
        String pass = "Milky@1234";
        driver.findElement(By.id("userEmail")).sendKeys(email);
        String prodName = "ZARA COAT 3";

        driver.findElement(By.cssSelector("input[id='userPassword'")).sendKeys(pass);

        driver.findElement(By.id("login")).click();
        //Product add to cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prodStream = products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);

        prodStream.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(prodName));
        Assert.assertTrue(match);

        driver.findElement(By.xpath("//button[text()='Checkout']")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "alb").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[1]")).click();

        driver.findElement(By.cssSelector(".actions a")).click();

        String messgProceded = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(messgProceded.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();

    }
}

