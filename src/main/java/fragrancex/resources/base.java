package fragrancex.resources;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class base {
    public WebDriver driver;
    public WebDriver initializeDriver() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\daddey\\Documents\\Automation\\bin\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
