package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void registrationPositiveTest(){
        System.out.println("Начало теста: регистрация");
        new HomePage(getDriver());
        System.out.println("HomePage загружена");
    }

    @Test
    void testRegistration() {
        assertTrue(true);
    }

}
