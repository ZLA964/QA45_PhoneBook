package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver webDrv) {
        setDriver(webDrv);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOut;

    @FindBy(xpath = "//a[@href='/add']")
    WebElement btnAddContact;

    public void setBtnAddContact() {
        btnAddContact.click();
    }

    public void clickBtnSignOut(){
        pause(3);
        btnSignOut.click();
    }

    public boolean isSingOut(){
       return btnSignOut.isDisplayed();
    }

}
