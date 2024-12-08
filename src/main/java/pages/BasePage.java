package pages;

import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    @Setter
    static WebDriver driver;

    public void pause(int time){
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementContainsText(WebElement element, String text){
        return element.getText().contains(text);
    }

    public void waitNewElementOnPage(String xpath, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    // wait new Element on Page
    public void waitNewElementOnPage(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.visibilityOf(element));
    }

}
