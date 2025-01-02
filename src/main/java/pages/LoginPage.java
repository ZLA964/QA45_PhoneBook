package pages;

import dto.UserDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver webDrv) {
        setDriver(webDrv);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@name='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@name='login']")
    WebElement btnLogin;

    @FindBy(xpath = "//button[@name='registration']")
    WebElement btnRegistration;

    @FindBy(xpath = "//div//div//div//div[@style]")
    WebElement errorMessage;
 //    "//div[@style='color: red; text-align: center; margin-bottom: 10px;']")

    public boolean validateErrorMessageLogin(String text){
        return isElementContainsText(errorMessage, text);
    }

    public void typeLoginForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
    }

    public void typeLoginForm(UserDto user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        btnLogin.click();
    }

    public void typeRegistrationForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnRegistration.click();
    }

    public void closeAlert() {
        try {
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
  //      System.out.println(alert.getText());
            String alertText = alert.getText();
//            logger.info("--- closeAlert -> Alert appeared with text: " + alertText);
            alert.accept();
            logger.info("--- closeAlert -> Alert accepted successfully.");
        } catch (TimeoutException e) {
            logger.warn("No alert appeared within the timeout period.");
        } catch (Exception e) {
            logger.info("An unexpected error occurred while handling the alert: " + e.getMessage());
        }
    }
}
