package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.abstractComponent;

import java.util.List;

public class orderPage extends abstractComponent {
    WebDriver driver;
    @FindBy(css = "tr td:nth-child(3)")
   private List<WebElement> ordersName;

    public orderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public boolean verifyOrdersDisplay(String prodName) {
        Boolean match = ordersName.stream().anyMatch(orderN -> orderN.getText().equalsIgnoreCase(prodName));
        return match;
    }

}
