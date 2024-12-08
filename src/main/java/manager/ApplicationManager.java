package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.TestsDates;

public class ApplicationManager {
    private WebDriver driver;
    private TestsDates testData;

    public WebDriver getDriver() {
        return driver;
    }

    public TestsDates getTestData() {
        return testData;
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        testData = new TestsDates(1);
        testData.setLogin(1, "1zlv964@gmail.com");
        testData.setPassword(1, "As12345!");

    }

    @AfterMethod
    public void tearDown() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        if (driver != null) {
//            driver.quit();
        }
    }
}
