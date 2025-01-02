package manager;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WebDrvListener;

import java.lang.reflect.Method;
// /import utils.TestsDates;

@Getter
public class ApplicationManager {
//    private WebDriver driver;
    private EventFiringWebDriver driver;

    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

// /   private TestsDates testData;

    @BeforeMethod
    public void setup(Method method) {
//        logger.info("Start testing --> " + method.getName());
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().window().maximize();
        driver.register(new WebDrvListener());
// /       testData = new TestsDates(1);
// /       testData.setLogin(1, "1zlv964@gmail.com");
// /       testData.setPassword(1, "As12345!");
    }

    @AfterMethod
    public void tearDown(Method method) {
 //       logger.info("Stop test --> " + method.getName());

        if (driver != null) {
          driver.quit();
        }
    }
}

// /       try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }