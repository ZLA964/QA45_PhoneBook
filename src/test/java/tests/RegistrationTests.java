package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends ApplicationManager {

    @Test
    void registrationPositiveTest(){
        new HomePage(getDriver());
    }

    @Test
    void testRegistration() {
        assertTrue(true);
    }

}
