package tests;

import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class AddContactTests extends ApplicationManager {
    UserDto user = new UserDto( "frodo_begin" + 1 + "@gmail.com", "P1password!_");
    AddPage addPage;


   @BeforeMethod
   public void login() {
       new HomePage(getDriver()).clickBtnLoginHeader();
       new LoginPage(getDriver()).typeLoginForm(user);
       new ContactsPage(getDriver()).clickBtnAdd();
       addPage = new AddPage(getDriver());

   }


    @Test
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





}
