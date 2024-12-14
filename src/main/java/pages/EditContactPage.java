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

    public void waitForInputsToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(inputsForEdit));
    }

    public void waitToLoadListFor(List<WebElement> elementsList) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(elementsList));
    }

    public void typeContactNewName(String newName){
        waitToLoadListFor(inputsForEdit);
        WebElement inputName = inputsForEdit.get(0);
        inputName.clear();
        inputName.sendKeys( newName);
    }

    private boolean detailsContactsCheckNotChange(String oldDetails) {
        if(oldDetails == null) return false;
        return oldDetails.equals(getContactDetails());
    }

    public void waitContactChange(){

    }

    public void changeContactName(String newName){
        String oldDetails = getContactDetails();
        btnEdit.click();
        typeContactNewName(newName);
        btnSaveEditDetails.click();
//        while (detailsContactsCheckNotChange(oldDetails));
        new WebDriverWait(driver, 3)
                .until(driver ->
                        !oldDetails.equals(getContactDetails()));
    }


    public void typeContactNewLastName(String newLastName){
        waitForInputsToLoad();
        WebElement inputLastName = inputsForEdit.get(1);
 //       inputName.clear();
        inputLastName.sendKeys(Keys.CONTROL + "a");
        inputLastName.sendKeys(Keys.BACK_SPACE);
        inputLastName.sendKeys( newLastName);
    }

    public void changeLastName(String newLastName){
        typeContactNewLastName(newLastName);
        btnSaveEditDetails.click();
    }
    public void typeContactNewPhone(String newPhone){
        waitForInputsToLoad();
        WebElement inputName = inputsForEdit.get(2);
        inputName.sendKeys( newPhone);
    }

    public void changePhone(String newPhone){
        typeContactNewPhone(newPhone);
        btnSaveEditDetails.click();
    }

    public void typeContactNewEmail(String newEmail){
        waitForInputsToLoad();
        inputsForEdit.get(3).sendKeys( newEmail);
    }

    public void changeEmail(String ewEmail){
        typeContactNewEmail(ewEmail);
        btnSaveEditDetails.click();
    }

    public void typeContactNewAddress(String newAddress){
        waitForInputsToLoad();
        inputsForEdit.get(4).sendKeys( newAddress);
    }
    public void changeAddress(String newAddress){
        typeContactNewAddress(newAddress);
        btnSaveEditDetails.click();
    }

    public void typeContactNewDescription(String newDescription){
        waitForInputsToLoad();
        inputsForEdit.get(5).sendKeys( newDescription);
    }

    public void changeDescription(String newDescription){
        typeContactNewDescription(newDescription);
        btnSaveEditDetails.click();
    }

    public void typeNewContactDetails(ContactDto contactDto){
        waitForInputsToLoad();
        inputsForEdit.get(0).sendKeys( contactDto.getName());
        inputsForEdit.get(1).sendKeys( contactDto.getLastName());
        inputsForEdit.get(2).sendKeys( contactDto.getPhone());
        inputsForEdit.get(3).sendKeys( contactDto.getEmail());
        inputsForEdit.get(4).sendKeys( contactDto.getAddress());
        inputsForEdit.get(5).sendKeys( contactDto.getDescription());
    }

    public void changeAllContactDetails(ContactDto contactDto){
        typeNewContactDetails(contactDto);
        btnSaveEditDetails.click();
    }


    public String getContactDetails(){
        waitNewElementOnPage(btnEdit,1);
        return contactDetails.getText();
    }

    WebDriverWait wait = new WebDriverWait(driver, 3);

    public int quantityContacts() {
        try{
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='contact-item_card__2SOIM']")))
                .size();
        } catch (org.openqa.selenium.TimeoutException e){
            System.out.println("No contacts in this PhoneBook");
            return -1;
        }
    }

    public ArrayList<WebElement> contactsList() {
        return new ArrayList<>( wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[h2 and h3]"))));
    }

    public void openFistContactCardForEdit(){
        clickWait(firstContactCard, 3);
//        clickWait(btnEdit,1);
    }

    public void openLastContactCardForEdit(){
        clickWait(lastContactCard, 3);
        clickWait(btnEdit,1);
    }

    public void openContactCardByIndexForEdit(int index){
        WebElement indexContactCard = contactsList().get(index);
        clickWait(indexContactCard, 0);
        clickWait(btnEdit,0);
    }

    public void openContactCardByIndex(int index){
        WebElement indexContactCard = contactsList().get(index);
        clickWait(indexContactCard, 0);
    }

    public String getDetailsContactByIndex(int index){
        openContactCardByIndex(index);
        return getContactDetails();
    }


}
