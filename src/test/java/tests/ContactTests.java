package tests;

import dto.ContactDto;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;


//import java.util.Random;

public class ContactTests extends ApplicationManager {
    ContactDto contactDto;

    @BeforeMethod
    public void loginRegisteredUser(){
        int i = 1;
//                new Random().nextInt(1000);
        String email = "frodo_begin" + i + "@gmail.com";
        String password = "P1password!_";
        UserDto user = new UserDto(email, password);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        new ContactsPage(getDriver()).setBtnAddContact();
        contactDto = ContactDto.builder()
                .name("Tree")
                .lastName("Green")
                .email("treegreen@mail.com")
                .phone("1234567890")
                .address("Forest")
                .description("Test Contact 01")
                .build();

    }

    @Test
    void isAddContactPage(){
        Assert.assertTrue(new AddContactPage(getDriver()).isSaveBtn());
    }

    @Test
    void addContactPositiveTest() {
//        new AddContactPage(getDriver()).typeContactData(contactDto);
        new AddContactPage(getDriver()).addNewContact(contactDto);
        String removedPhone = new AddContactPage(getDriver()).removeContact(contactDto);
//        System.out.println(removedPhone);
        Assert.assertEquals(contactDto.getPhone(), removedPhone);
    }
}
