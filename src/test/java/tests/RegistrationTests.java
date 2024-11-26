package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class RegistrationTests extends ApplicationManager {

    @Test
    void registrationPositiveTest(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage((getDriver())).typeLoginForm("email", "P1password");
    }

}
