package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import rahulshettyacademy.abstractComponents.abstractComponent;

public class CheckOutPage extends abstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }

    @FindBy(css = ".actions a")
    WebElement submit;
    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[1]")
    WebElement selectCountry;

    By Results = By.cssSelector(".ta-results");

    public void SelectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        selectCountry.click();
    }

    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
