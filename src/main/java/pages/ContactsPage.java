package pages;

import dto.ContactDto;
import dto.ContactDtoLombok;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = "//a[text()='ADD']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div/div[last()]")
    WebElement lastElementContactList;




    public boolean validateLastElementContactList(ContactDtoLombok contact){
        System.out.println(lastElementContactList.getText());
        return lastElementContactList.getText().contains(contact.getName());
    }

    public boolean validateLastElementContactList(ContactDto contact){
        System.out.println(lastElementContactList.getText());
        return lastElementContactList.getText().contains(contact.getName());
    }

    public void clickBtnAdd(){
        btnAdd.click();
    }

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
