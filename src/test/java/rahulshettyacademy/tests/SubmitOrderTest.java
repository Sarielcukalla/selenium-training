package rahulshettyacademy.tests;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testComponents.BaseTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String prodName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        ProductCatalog productCatalog = landingPage.LoginApplication(input.get("email"), input.get("password"));
        //Product add to cart
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("products"));
        cartPage cartpage = productCatalog.goToCartPage();
        Boolean match = cartpage.verifyProductsDisplay(input.get("products"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartpage.goToCheckout();
        checkOutPage.SelectCountry("alb");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmMessage = confirmationPage.GetConfMess();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println(confirmMessage);
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        ProductCatalog productCatalog = landingPage.LoginApplication("cukallasariel@gmail.com", "Milky@1234");
        orderPage ordersPages = productCatalog.goToOrdersPage();
        Assert.assertTrue(ordersPages.verifyOrdersDisplay(prodName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}

