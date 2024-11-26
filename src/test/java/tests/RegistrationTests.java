package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

public class RegistrationTests extends ApplicationManager {

    @Test
    void registrationPositiveTest(){
        new HomePage(getDriver());
    }

}
