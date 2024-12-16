package pages;

import dto.ContactDto;
import dto.ContactDtoLombok;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement firstElementContactList;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemove;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;

/*/    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContacts;
*/
/*/   public void clickBtnContacts() {

        btnContacts.click();
    }
*/

/*/    public void clickBtnEdit() {
        clickWait(btnEdit, 2);
    }
*/

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement inputAddress;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement btnSave;
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']")
    WebElement cardContact;


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
/* /        wait.until(ExpectedConditions
                    .numberOfElementsToBeLessThan(By.xpath("//div[@class='contact-item_card__2SOIM']"), counts));
               wait.until(driver -> quantityContacts() != counts);
           pause(3);
 */
    }

/* /    public void openFistContactForEdit() {
        clickWait(firstElementContactList, 3);
 //  /     clickWait(btnEdit, 1);
        clickBtnEdit();
    }
*/

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
        // /    pause(3);
        // /  btnSignOut.click();
        clickWait(btnSignOut, 3);
    }

    public boolean isSingOut() {
        return btnSignOut.isDisplayed();
    }


    public int quantityContacts() {
// /       pause(5);
// /       return driver.findElements(By.xpath(
// /               "//div[@class='contact-item_card__2SOIM']")).size();

        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//div[@class='contact-item_card__2SOIM']")))
                    .size();
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("No contacts in this PhoneBook");
            return -1;
        }
    }

    public void editContact(ContactDtoLombok contact) {
        firstElementContactList.click();
        btnEdit.click();

        inputName.clear();
        inputName.sendKeys(contact.getName());
        inputLastName.clear();
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.clear();
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.clear();
        inputEmail.sendKeys(contact.getEmail());
        inputAddress.clear();
        inputAddress.sendKeys(contact.getAddress());
        btnSave.click();
    }

    public boolean validateCardContact(ContactDtoLombok contact) {
        System.out.println(cardContact.getText());
        System.out.println("============");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .textToBePresentInElement(cardContact, contact.getName()));
        System.out.println(cardContact.getText());
        String cardContactText = cardContact.getText();

        return (cardContactText.contains(contact.getName()) &&
                cardContactText.contains(contact.getLastName()) &&
                cardContactText.contains(contact.getPhone()) &&
                cardContactText.contains(contact.getEmail()) &&
                cardContactText.contains(contact.getAddress())
        );
    }
}
