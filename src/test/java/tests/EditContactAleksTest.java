package tests;

import data_provader.DPContact;
import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.*;

public class EditContactAleksTest extends ApplicationManager {
    UserDto user = new UserDto( "frodo_begin" + 1 + "@gmail.com", "P1password!_");
    ContactsPage contactsPage;

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void login() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
    }

    @Test
    public void editContactPositive1Test(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .phone(generatePhone(10))
                .address("Address " + generateString(14))
                .build();
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }

    @Test(dataProvider = "newContactsDP", dataProviderClass = DPContact.class)
    public void editContactTestDP(ContactDtoLombok contact){
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }

    @Test(dataProvider = "newContactsDPFile", dataProviderClass = DPContact.class)
    public void editContactTestDPFile(ContactDtoLombok contact){
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }

}
