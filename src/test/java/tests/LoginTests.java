package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestsDates;

public class LoginTests extends ApplicationManager {

    @Test
    void loginPositiveTest(){
        getDriver().get("https://telranedu.web.app/login");
        TestsDates testData = getTestData();
        LoginPage onLoginPage = new LoginPage( getDriver());
        onLoginPage.typeLoginForm(
                testData.getLogin(1),
                      testData.getPassword(1));
        onLoginPage.pause(1);
        onLoginPage.clickBtnLogin();
    }
}
