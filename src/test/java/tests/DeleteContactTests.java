package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class DeleteContactTests extends ApplicationManager {
    UserDto user = new UserDto( "frodo_begin" + 1 + "@gmail.com", "P1password!_");
    ContactsPage contactsPage;

    @BeforeMethod
    public void login() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
    }

    @Test
    public void deleteContactPositiveTest(){
        int quantityBeforeDelete = contactsPage.quantityContacts();
        System.out.println(quantityBeforeDelete);
        contactsPage.deleteFirstContact();
        int quantityAfterDelete =  contactsPage.quantityContacts();
        System.out.println("quantityAfterDelete--> " + quantityAfterDelete);
        Assert.assertEquals(quantityBeforeDelete -1 , quantityAfterDelete);
    }

}
