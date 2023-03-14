package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.abstractComponent;

import java.util.List;

public class ProductCatalog extends abstractComponent {
    WebDriver driver;

    //constructor
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //factoryobjects
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css = ".ng-animating")
    WebElement spinner;



    By productsBy = By.cssSelector(".mb-3");
    By addProductBy = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By ngAnimating = By.cssSelector(".ng-animating");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String prodName) {
        WebElement prodStream = getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
        return prodStream;
    }

    public void addProductToCart(String prodName) throws InterruptedException {
        WebElement prod = getProductByName(prodName);
        prod.findElement(addProductBy).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }


}


