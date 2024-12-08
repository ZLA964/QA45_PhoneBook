package pages;

import dto.ContactDto;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class AddContactPage {

    @FindBy(xpath = "//div[@class='add_form__2rsm2']//input")
    private List<WebElement> inputContactData;

    @FindBy(xpath = "//button[b]")
    WebElement btnSave;

    public void clickBtnSave(){
        btnSave.click();
    }

    public void typeContactData(ContactDto contactDto){
        if(contactDto == null) return;
        Iterator<WebElement> inputs = inputContactData.iterator();
        Iterator<String> details =contactDto.getContactDetails().iterator();
        while (inputs.hasNext() && details.hasNext()){
            inputs.next().sendKeys(details.next());
        }

    }
}
