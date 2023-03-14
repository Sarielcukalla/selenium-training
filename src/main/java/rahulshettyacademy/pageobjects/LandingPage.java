package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.abstractComponent;

public class LandingPage extends abstractComponent {
    WebDriver driver;

    //constructor
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }

    //    WebElement userEmail = driver.findElement(By.id("userEmail"));
    //pageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessg;

    public ProductCatalog LoginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;

    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessg);
        return errorMessg.getText();
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");

    }


}
