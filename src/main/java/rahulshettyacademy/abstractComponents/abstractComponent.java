package rahulshettyacademy.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.orderPage;

import java.time.Duration;

public class abstractComponent {
    WebDriver driver;

    public abstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='/dashboard/cart']")
    WebElement cartHeader;
    @FindBy(css = "[routerlink*='/dashboard/myorders']")
    WebElement orderHeader;


    public cartPage goToCartPage() {
        cartHeader.click();
        cartPage cartpage = new cartPage(driver);
        return cartpage;


    }
    public orderPage goToOrdersPage() {
        orderHeader.click();
        orderPage orderpage = new orderPage(driver);
        return orderpage ;

    }

    public void waitForElementToAppear(By findby) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));


    }
    public void waitForWebElementToAppear(WebElement findby) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findby));
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(ele));


    }
}
