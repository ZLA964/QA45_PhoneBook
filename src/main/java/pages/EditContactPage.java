package pages;

import dto.ContactDto;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class EditContactPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, 3);

    public EditContactPage(WebDriver webDrv) {
        setDriver(webDrv);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[h2 and h3]")
    WebElement firstContactCard;

    @FindBy(xpath = "//div[h2 and h3]")
    List<WebElement> contactCardsList;

    @FindBy(xpath = "//div[h2 and h3][last()]")
    WebElement lastContactCard;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;

    @FindBy(xpath = "//button[text()='Edit']/..")
    WebElement contactDetails;

    @FindBy(xpath = "//div[@class='form_form__FOqHs']//input")
    List<WebElement> inputsForEdit;

    @FindBy(xpath = "//div[@class='form_form__FOqHs']//button")
    WebElement btnSaveEditDetails;

    private void waitForInputsToLoad() {
        wait.until(ExpectedConditions.visibilityOfAllElements(inputsForEdit));
    }

    private void waitContactChange(String oldDetails, int seconds) {
        try {
            new WebDriverWait(driver, seconds)
                    .until(driver ->
                            !oldDetails.equals(getContactDetails()));
        } catch (Exception e) {
            System.out.println("No contact change!");
        }
    }
// it was variant that not like to repeat six time ))
/* it was variant that not like to repeat six time ))
    public void changeContactName(String newName){
        String oldDetails = getContactDetails();
        btnEdit.click();
        typeContactNewName(newName);
        btnSaveEditDetails.click();
        waitContactChange(oldDetails,1);
    }
*/

    private void typeNewContactField(int field, String value) {
        waitForInputsToLoad();
        WebElement input = inputsForEdit.get(field);
        input.clear();
        input.sendKeys(value);
    }

    private void clickAndWait(Runnable change) {
        String oldDetails = getContactDetails();    // Get the current contact details
        btnEdit.click();                            // Click the edit button
        change.run();                               // Execute the provided action (e.g., entering a new name)
        btnSaveEditDetails.click();                 // Click the save button
        waitContactChange(oldDetails, 5);   // Wait for the contact details to change
    }

    public void changeName(String newName) {
        clickAndWait(() -> typeContactNewName(newName));
    }

    private void typeContactNewName(String newName) {
        typeNewContactField(0, newName);
    }


    public void changeLastName(String newLastName) {
        clickAndWait(() -> typeContactNewLastName(newLastName));
    }

    private void typeContactNewLastName(String newLastName) {
        typeNewContactField(1, newLastName);
// variant
/* use Keys.
        waitForInputsToLoad();
        WebElement inputLastName = inputsForEdit.get(1);
        inputLastName.sendKeys((Keys.COMMAND + "A"));
        inputLastName.sendKeys(Keys.BACK_SPACE);
        inputLastName.sendKeys(newLastName);
*/
    }


    public void changePhone(String newPhone) {
        clickAndWait(() -> typeContactNewPhone(newPhone));
    }

    private void typeContactNewPhone(String newPhone) {
        typeNewContactField(2, newPhone);
    }


    private void typeContactNewEmail(String newEmail) {
        typeNewContactField(3, newEmail);
    }

    public void changeEmail(String newEmail) {
        clickAndWait(()->typeContactNewEmail(newEmail));
    }

    private void typeContactNewAddress(String newAddress) {
        typeNewContactField(4, newAddress);
    }

    public void changeAddress(String newAddress) {
        clickAndWait(()->typeContactNewAddress(newAddress));
    }

    private void typeContactNewDescription(String newDescription) {
        typeNewContactField(5, newDescription);
    }

    public void changeDescription(String newDescription) {
        clickAndWait(()->typeContactNewDescription(newDescription));
    }

    public void typeNewContactDetails(ContactDto contactDto) {
        waitForInputsToLoad();
        for(WebElement input : inputsForEdit){
            String field = input.getAttribute("placeholder");
            if( !field.contains("desc")){
                input.clear();
            }                                 // this loop is not enough !
        }
        inputsForEdit.get(0).sendKeys(contactDto.getName());
        inputsForEdit.get(1).clear();         // must clear after each sendKeys !!!
        inputsForEdit.get(1).sendKeys(contactDto.getLastName());
        inputsForEdit.get(2).clear();
        inputsForEdit.get(2).sendKeys(contactDto.getPhone());
        inputsForEdit.get(3).clear();
        inputsForEdit.get(3).sendKeys(contactDto.getEmail());
        inputsForEdit.get(4).clear();
        inputsForEdit.get(4).sendKeys(contactDto.getAddress());
 //       inputsForEdit.get(5).sendKeys(contactDto.getDescription());    // BAG !!!
    }

    public void changeAllContactDetails(ContactDto contactDto) {
        clickAndWait(() -> typeNewContactDetails(contactDto));
    }

    public String getContactDetails() {
        waitNewElementOnPage(btnEdit, 3);
        return contactDetails.getText();
    }



    public ArrayList<WebElement> contactsList() {
        return new ArrayList<>(wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[h2 and h3]"))));
    }

    public void openFistContactCardForEdit() {
        clickWait(firstContactCard, 0);
    }

    public void openLastContactCardForEdit() {
        clickWait(lastContactCard, 0);
    }

    public void openContactCardByIndexForEdit(int index) {
        WebElement indexContactCard = contactsList().get(index);
        clickWait(indexContactCard, 0);
    }

    public void openContactCardByIndex(int index) {
        WebElement indexContactCard = contactsList().get(index);
        clickWait(indexContactCard, 0);
    }

    public String getDetailsContactByIndex(int index) {
        openContactCardByIndex(index);
        return getContactDetails();
    }

}
