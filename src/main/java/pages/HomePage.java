package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver webDrv){
        setDriver(webDrv);
   //     driver.get("https://telranedu.web.app/home");
        driver.get(getProperty("login.properties", "urlStart"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;

    public void clickBtnLoginHeader(){
        btnLogin.click();
    }
/*
    @FindBy(xpath = "//a[@href='/about']" )
    WebElement btnAbout;

    public void clickBtnAbout(){
        btnAbout.click();
    }
    not use also
 */
}
