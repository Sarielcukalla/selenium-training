package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.abstractComponent;

import java.util.List;

public class cartPage extends abstractComponent {

    WebDriver driver;
    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;
    @FindBy(css = ".cartSection h3")
    private List<WebElement> ProductsTitle;

    public cartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductsDisplay(String prodName) {
        Boolean match = ProductsTitle.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(prodName));
        return match;
    }

    public CheckOutPage goToCheckout() {
        checkoutEle.click();
        return new CheckOutPage(driver);


    }
}
