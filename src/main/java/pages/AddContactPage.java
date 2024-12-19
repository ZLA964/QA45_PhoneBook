package pages;

import dto.ContactDto;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AddContactPage extends BasePage {

    public AddContactPage(WebDriver webDrv) {
        setDriver(webDrv);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@class='add_form__2rsm2']//input")
    List<WebElement> inputContactData;

    @FindBy(xpath = "//button[b]")
    WebElement btnSave;
    public void clickBtnSave() {
//        btnSave.click();
        clickWait(btnSave, 3);
    }

    @FindBy(xpath = "//div[h2 and h3]")
    List<WebElement> cards;

/*/    public WebElement getLastCard() {
        return cards.get(cards.size() - 1);
    } */

    public int getNumberOfCards() {
        return cards.size();
    }

    @FindBy(xpath = "//div[h2 and h3][last()]")
    WebElement lastContactCard;

/* /    @FindBy(xpath = "//div[h2 and h3]//h2")
    WebElement contactName;
*/

    @FindBy(xpath = "//div[h2 and h3][last()]//h2")
    WebElement lastContactName;

/* /     @FindBy(xpath = "//div[h2 and h3]//h3")
    WebElement contactPhone;
*/

    @FindBy(xpath = "//div[h2 and h3][last()]//h3")
    WebElement lastContactPhone;

    @FindBy(xpath = "//div//..//button[text()='Remove']")
    WebElement btnRemove;

/*/    @FindBy(xpath = "//div//..//button[text()='Remove']/..")
    WebElement contactDetails;
*/
/* /    public String getContactDetails(){
        return contactDetails.getText();
    }
*/

    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContacts;

    public void clickBtnContacts() {
        btnContacts.click();
    }

    public boolean isContactCard() {
        try {
            new WebDriverWait(driver, 1)  // what is time need check ???!
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[h2 and h3]")));
            return true;
        } catch (TimeoutException e) {
            System.out.println("No contact card is visible");
            return false;
        }
    }

    public void typeContactData(ContactDto contactDto) {
        if (contactDto == null) return;
        Iterator<WebElement> inputs = inputContactData.iterator();
        Iterator<String> details = contactDto.getContactDetails().iterator();
        while (inputs.hasNext() && details.hasNext()) {
            WebElement input = inputs.next();
            String detail = details.next();
            if (detail != null) input.sendKeys(detail);
        }
    }

    public void addNewContact(ContactDto contact) {
        if (contact == null) return;
        typeContactData(contact);
        clickBtnSave();
        waitNewElementOnPage(lastContactCard, 1);
//        checkLastContactCard(contact);
    }

    public boolean isSaveBtn() {
        return btnSave.isDisplayed();
    }

    public void clickOnLastContactCard(ContactDto contact) {
        if (checkLastContactCard(contact))    lastContactCard.click();
        else {
            System.out.println("Wrong contact on card");
        }
    }

    private boolean checkLastContactCard(ContactDto contactDto) {
        if (contactDto == null) return false;
        waitNewElementOnPage(lastContactCard, 1);
        Boolean isName = lastContactName.getText().equalsIgnoreCase(contactDto.getName());
        Boolean isPhone = lastContactPhone.getText().equalsIgnoreCase(contactDto.getPhone());
        return isName && isPhone;
    }

    public String removeLastContact(ContactDto contact) {
        String phone = "-1";
        try {
            clickOnLastContactCard(contact);
            phone = lastContactPhone.getText();
            waitNewElementOnPage("//div//..//button[text()='Remove']", 5);
            int numCards = getNumberOfCards();
            if(numCards==0) {
                return phone;
            }
            int waitNumCards = numCards-1;
 //           System.out.println(getContactDetails());
            btnRemove.click();
            while (numCards > waitNumCards) {
                numCards = getNumberOfCards();
            }                                      // wait contact is removed!
        } catch (NoSuchElementException e) {
            System.out.println("No contact for remove");
        }
        return phone;
    }

    public boolean isAlertCorrect(String text) {
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        if (text == null || alert.getText() == null) {
            return false;
        }
        String alertText = alert.getText();
//  print if you need full alert text      System.out.println(alertText);
        alert.accept();
        return alertText.contains(text);
    }

/* /
    WebDriverWait wait = new WebDriverWait(driver, 3);
*/


}
