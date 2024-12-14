package pages;

import dto.ContactDto;
import dto.ContactDtoLombok;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPathExpression;

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

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement firstElementContactList;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemove;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;

    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContacts;

    public void clickBtnContacts() {
        btnContacts.click();
    }

    public void clickBtnEdit(){
        clickWait(btnEdit,2);
    }

    WebDriverWait wait = new WebDriverWait(driver, 3);

    public void waitCutList(String xpath, int initialCounts) {
        try {
            wait.until(ExpectedConditions
                    .numberOfElementsToBeLessThan(By.xpath(xpath), initialCounts));
//              System.out.println("success delete element in list");
        } catch (TimeoutException e) {
            System.out.println("not delete element in list");
        }
    }

    public void deleteFirstContact() {
        clickWait(firstElementContactList, 3);
        int counts = quantityContacts();
        clickWait(btnRemove, 2);
        waitCutList("//div[@class='contact-item_card__2SOIM']", counts);
//        wait.until(ExpectedConditions
//                .numberOfElementsToBeLessThan(By.xpath("//div[@class='contact-item_card__2SOIM']"), counts));
        //       wait.until(driver -> quantityContacts() != counts);
        //   pause(3);
    }

    public void openFistContactForEdit(){
        clickWait(firstElementContactList, 3);
        clickWait(btnEdit,1);
    }


    public boolean validateLastElementContactList(ContactDtoLombok contact) {
        System.out.println(lastElementContactList.getText());
        return lastElementContactList.getText().contains(contact.getName());
    }

    public boolean validateLastElementContactList(ContactDto contact) {
        System.out.println(lastElementContactList.getText());
        return lastElementContactList.getText().contains(contact.getName());
    }

    public void clickBtnAdd() {
        btnAdd.click();
    }

    public void setBtnAddContact() {
        btnAddContact.click();
    }

    public void clickBtnSignOut() {
        //    pause(3);
        //    btnSignOut.click();
        clickWait(btnSignOut, 3);
    }

    public boolean isSingOut() {
        return btnSignOut.isDisplayed();
    }


    public int quantityContacts() {
//        pause(5);
//        return driver.findElements(By.xpath(
//                "//div[@class='contact-item_card__2SOIM']")).size();

        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//div[@class='contact-item_card__2SOIM']")))
                    .size();
        } catch (org.openqa.selenium.TimeoutException e){
            System.out.println("No contacts in this PhoneBook");
            return -1;
        }
    }
}
