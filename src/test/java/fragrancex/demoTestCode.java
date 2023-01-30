package fragrancex;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import fragrancex.pageObjects.homePage;
import fragrancex.resources.base;

public class demoTestCode extends base {
    homePage home;

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
    }
    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void fragrancexTest() throws IOException {
        /* Go to Website */
        driver.get("https://www.fragrancex.com/");
        home = new homePage(driver);
        home.closeInitialDialog();
        /* Store all links in an object */
        List<WebElement> allLinks = home.getAllLinks();
        /* Log and Store all perfume names found under "Top Picks" */
        for (WebElement topPicksProduct : home.topPicks) {
            System.out.println(topPicksProduct.getText());
        }
        /* Click on the 3rd product on Top Picks */
        for (WebElement topPick : home.selectTopPicksList(3)) {
            String selectedProductName = topPick.getText();
            System.out.println(selectedProductName);
            topPick.click();
            String productName = home.productName.getText();
        /* Verify the Product name */
            assertTrue("Product Name not found", productName.contains(selectedProductName));
        }
        String cartCount = home.getCartCount();
        /* Add the 2nd product variant to the bag */
        home.addToCartList2.get(2).click();
        String updatedCartCount = home.getCartCount();
        /* Verify Bag count update */
        Assert.assertEquals(Integer.parseInt(updatedCartCount), Integer.parseInt(cartCount.replaceAll("[^0-9]", "")) + 1);
        Select select = new Select(home.cartQuantity);
        /* Update Quantity */
        select.selectByVisibleText("5");
    }

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] { demoTestCode.class });
        testNG.run();
    }
}

