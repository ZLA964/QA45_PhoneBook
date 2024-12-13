package tests;

import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class AddContactTests extends ApplicationManager {
    UserDto user = new UserDto( "frodo_begin" + 1 + "@gmail.com", "P1password!_");
    AddPage addPage;

    SoftAssert softAssert = new SoftAssert();

   @BeforeMethod
   public void login() {
       new HomePage(getDriver()).clickBtnLoginHeader();
       new LoginPage(getDriver()).typeLoginForm(user);
       new ContactsPage(getDriver()).clickBtnAdd();
       addPage = new AddPage(getDriver());
   }


    @Test(invocationCount = 9)
    public void addNewContactPositiveTest(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Tree")
                .lastName("Green1023")
                .email("treegreen@mail.com")
                .phone("1234567890")
                .address("Forest")
                .description("Test Contact 01")
                .build();
        addPage.typeContactForm(contact);
        addPage.clickBtnSave();
        Assert.assertTrue(new ContactsPage(getDriver()).validateLastElementContactList(contact));
    }

    @Test
    public void addNewContactNegativeTest_emptyName(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Tree")
                .lastName("Green1023")
                .email("treegreen@mail.com")
                .phone("12347890")
                .address("Forest")
                .description("Test Contact 01")
                .build();
        addPage.typeContactForm(contact);
        addPage.clickBtnSave();
        Assert.assertFalse(new AddPage(getDriver()).validateContacts());
    }

    @Test
    public void addNewContactNegativeTest_wrongPhone(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Tree")
                .lastName("Green1023")
                .email("treegreen@mail.com")
                .phone("1234hhh567890")
                .address("Forest")
                .description("Test Contact 01")
                .build();
        addPage.typeContactForm(contact);
        addPage.clickBtnSave();
        String message = addPage.closeAlertAndReturnText();
        System.out.println(message);
        softAssert.assertTrue( message.contains("Phone not valid: Phone number must"),
                "assert message contains not pass");
        softAssert.assertAll();
    }

    @Test
    public void addNewContactNegativeTest_wrongEmail(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Tree")
                .lastName("Green1023")
                .email("treegreen@mail..com")
                .phone("1234567890")
                .address("Forest")
                .description("Test Contact 01")
                .build();
        addPage.typeContactForm(contact);
        addPage.clickBtnSave();
        String message = addPage.closeAlertAndReturnText();
        System.out.println(message);
        softAssert.assertTrue( message.contains("Email not valid: must be a well-formed"),
                "assert message contains not pass");
        softAssert.assertAll();
    }

}
