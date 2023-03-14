package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import rahulshettyacademy.abstractComponents.abstractComponent;

public class ConfirmationPage extends abstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    public String GetConfMess() {
        return confirmationMessage.getText();
    }

}
