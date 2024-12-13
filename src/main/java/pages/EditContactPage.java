package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class EditContactPage extends BasePage {

    public EditContactPage(WebDriver webDrv) {
        setDriver(webDrv);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[h2 and h3]")
    WebElement firstContactCard;

    @FindBy(xpath = "//div[h2 and h3][last()]")
    WebElement lastContactCard;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;

    @FindBy(xpath = "//button[text()='Edit']/..")
    WebElement contactDetails;

    public String getContactDetails(){
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
        clickWait(btnEdit,1);
    }

    public void openLastContactCardForEdit(){
        clickWait(lastContactCard, 3);
        clickWait(btnEdit,1);
    }

    public void openContactCardByIndexForEdit(int index){
        WebElement indexContactCard = contactsList().get(index);
        clickWait(indexContactCard, 3);
        clickWait(btnEdit,1);
    }

    public void openContactCardByIndex(int index){
        WebElement indexContactCard = contactsList().get(index);
        clickWait(indexContactCard, 3);
    }

    public String getDetailsContactByIndex(int index){
        openContactCardByIndex(index);
        return getContactDetails();
    }


}
