package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestsDates;

public class LoginTests extends ApplicationManager {

    @Test
    void loginPositiveTest(){
//       getDriver().get("https://telranedu.web.app/login");
        new HomePage(getDriver()).clickBtnLoginHeader();
        TestsDates testData = getTestData();
        LoginPage onLoginPage = new LoginPage( getDriver());
        onLoginPage.typeLoginForm(
                testData.getLogin(1),
                      testData.getPassword(1));

    }
}
