package pages;

import dto.ContactDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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
        btnSave.click();
    }

    @FindBy(xpath = "//div[h2 and h3]")
    WebElement contactCard;

    @FindBy(xpath = "//div[h2 and h3]//h2")
    WebElement contactName;

    @FindBy(xpath = "//div[h2 and h3]//h3")
    WebElement contactPhone;

    @FindBy(xpath = "//div//..//button[text()='Remove']")
    WebElement btnRemove;



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

    public void addNewContact(ContactDto contactDto) {
        if (contactDto == null) return;
        typeContactData(contactDto);
        clickBtnSave();
    }

    public boolean isSaveBtn() {
        return btnSave.isDisplayed();
    }

    public void clickOnContactCard(ContactDto contactDto) {
        if( checkContactCard(contactDto))   contactCard.click();
        else {
            System.out.println("Wrong contact on card");
        }
    }

    public boolean checkContactCard(ContactDto contactDto) {
        if(contactDto == null ) return false;
        waitNewElementOnPage(contactCard,1);
        Boolean isName = contactName.getText().equalsIgnoreCase(contactDto.getName());
        Boolean isPhone = contactPhone.getText().equalsIgnoreCase(contactDto.getPhone());
        return  isName && isPhone;
    }

    public String removeContact(ContactDto contactDto){
        String phone = "-1";
        try {
        clickOnContactCard(contactDto);
        phone = contactPhone.getText();
        waitNewElementOnPage("//div//..//button[text()='Remove']", 1);
        btnRemove.click();
        } catch (NoSuchElementException e) {
            System.out.println("No contact for remove");
        }
        return phone;
    }
}
