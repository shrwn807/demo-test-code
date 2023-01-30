package fragrancex.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homePage {
    public WebDriver driver;

    @FindBy(xpath = "//div[@class='coupon-container']")
    public WebElement initialDialog;

    @FindBy(xpath = "//div[@class='coupon-container']//img[@class='close-popup']")
    public WebElement closeInitialDialogBtn;

    @FindBy(xpath = "//a")
    private List<WebElement> allLinks;

    @FindBy(xpath = "//div[@class='desc-section']/div[1]")
    private List<WebElement> productNames;

    @FindBy(xpath = "//div[@class='trending']//div[@class='content-container']//div[@class='desc-section']/div[1]")
    public List<WebElement> topPicks;
    
    @FindBy(xpath = "//h1[@class='product-header-name']/span")
    public WebElement productName;

    @FindBy(xpath = "//div[@class='product media']//div[@class='add-to-cart']//button")
    public List<WebElement> addToCartList2;
    
    @FindBy(xpath = "//div[@id='AjaxTopCart']//div")
    public WebElement cartCount;
    
    @FindBy(xpath = "//select[@class='cart-qty-select']")
    public WebElement cartQuantity;
    
    public homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public List<WebElement> getAllLinks() {
        return allLinks;
    }
    public List<WebElement> getProductNames() {
        return productNames;
    }
    public List<WebElement> selectTopPicksList(Number itemNumber) {
        return driver.findElements(By.xpath(String.format("//div[@class='trending']//div[@class='content-container'][%d]//div[@class='desc-section']/div[1]", itemNumber, null)));
    }
    public List<WebElement> addToCartBtn(Number row) {
        return driver.findElements(By.xpath(String.format("//div[@class='product media'][%d]//div[@class='add-to-cart']//button", row, null)));
    }

    public void closeInitialDialog() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By popupCoupon = By.xpath("//div[@class='coupon-container']//img[@class='close-popup']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupCoupon));
        closeInitialDialogBtn.click();
    }

    public String getCartCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By cartCount2 = By.xpath("//div[@id='AjaxTopCart']//div");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartCount2));
        String cartValue = this.cartCount.getText();
        return cartValue;
    }

}
