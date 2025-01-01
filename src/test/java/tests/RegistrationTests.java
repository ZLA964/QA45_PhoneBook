package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import java.util.Random;
@Listeners(TestNGListener.class)
public class RegistrationTests extends ApplicationManager {

    @Test
    void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage((getDriver())).typeRegistrationForm(
                "frodo_begin"+i+"@gmail.com", "P1password!_");
        Assert.assertTrue(new ContactsPage(getDriver()).isSingOut());
    }

    @Test
    void registrationNegativeTest_wrongPassword(){
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage((getDriver())).typeRegistrationForm(
                "frodo_begin"+i+"@gmail.com", "P1password");
        new LoginPage(getDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateErrorMessageLogin("Registration failed"));
    }

    @Test
    void registrationNegativeTest_wrongEmail(){
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage((getDriver())).typeRegistrationForm(
                "frodo_begin"+i+"gmail.com", "P1password!_");
        new LoginPage(getDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateErrorMessageLogin("Registration failed"));
    }

}
