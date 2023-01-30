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

        driver.get("https://www.fragrancex.com/");
        home = new homePage(driver);
        home.closeInitialDialog();

        List<WebElement> allLinks = home.getAllLinks();

        for (WebElement topPicksProduct : home.topPicks) {
            System.out.println(topPicksProduct.getText());
        }

        for (WebElement topPick : home.selectTopPicksList(3)) {
            String selectedProductName = topPick.getText();
            System.out.println(selectedProductName);
            topPick.click();
            String productName = home.productName.getText();
            assertTrue("Product Name not found", productName.contains(selectedProductName));
        }

        //String cartCount = home.cartCount.getText();
        String cartCount = home.getCartCount();
        home.addToCartList2.get(2).click();
        
        String updatedCartCount = home.getCartCount();
        Assert.assertEquals(Integer.parseInt(updatedCartCount), Integer.parseInt(cartCount.replaceAll("[^0-9]", "")) + 1);
        Select select = new Select(home.cartQuantity);
        select.selectByVisibleText("5");

    }

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] { demoTestCode.class });
        testNG.run();
    }
}

