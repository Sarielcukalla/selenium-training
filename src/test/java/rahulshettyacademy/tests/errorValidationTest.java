package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.testComponents.BaseTest;
import rahulshettyacademy.testComponents.Retry;

import java.io.IOException;
import java.util.List;

public class errorValidationTest extends BaseTest {

    @Test(groups = {"errorHandling"},retryAnalyzer = Retry.class)
    public void LoginError() throws IOException, InterruptedException {
        landingPage.LoginApplication("cukallasarel@gmail.com", "Milky@34");
        Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());

    }

    @Test
    public void ProductsError() throws IOException, InterruptedException {
        String prodName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.LoginApplication("cukallasariel@gmail.com", "Milky@1234");
        //Product add to cart
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(prodName);
        cartPage cartpage = productCatalog.goToCartPage();
        Boolean match = cartpage.verifyProductsDisplay("ZARA COAT 33");
        Assert.assertFalse(match);


    }
}
