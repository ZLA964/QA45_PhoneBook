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

import java.util.Random;

@Listeners(TestNGListener.class)
public class LoginTestInClassTest extends ApplicationManager {
    private String email;
    private String password;

    @BeforeMethod
    public void registration(){
        int i = new Random().nextInt(1000);
        email = "frodo_begin"+i+"@gmail.com";
        password = "P1password!_";
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage((getDriver())).typeRegistrationForm(
                email, password);
        new ContactsPage(getDriver()).clickBtnSignOut();
    }

    @Test
    public void loginPositiveTest(){
        UserDto user = new UserDto(email,password);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new ContactsPage(getDriver()).isSingOut());
    }

    @Test
    public void loginNegativeTest_emptyEmail(){
        UserDto user = new UserDto("",password);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        new LoginPage(getDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateErrorMessageLogin("Login Failed with "));
    }

}
