package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

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
    public void editContactPositiveTest(){

    }
}
