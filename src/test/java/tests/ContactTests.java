package tests;

import dto.ContactDto;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;


//import java.util.Random;

public class ContactTests extends ApplicationManager {
    ContactDto contactDto;
    UserDto user = new UserDto("frodo_begin" + 1 + "@gmail.com", "P1password!_");
    AddContactPage addContactPage;

    @BeforeMethod
    public void loginRegisteredUser() {
        int i = 1;
//                new Random().nextInt(1000);
        String email = "frodo_begin" + i + "@gmail.com";
        String password = "P1password!_";
        UserDto user = new UserDto(email, password);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        new ContactsPage(getDriver()).setBtnAddContact();
        addContactPage = new AddContactPage(getDriver());
        contactDto = ContactDto.builder()
                .name("Tree")
                .lastName("Green")
                .email("treegreen@mail.com")
                .phone("1234567890")
                .address("Forest")
                .description("Test Contact 01")
                .build();

    }

    @Test(priority = -2)
    void isAddContactPage() {
        Assert.assertTrue(new AddContactPage(getDriver()).isSaveBtn());
    }

    @Test(priority = -1)
    void addFirstContactPositiveTest() {
        addContactPage.addNewContact(contactDto);
//        String removedPhone = addContactPage.removeLastContact(contactDto);
//        System.out.println(removedPhone);
//        Assert.assertEquals(contactDto.getPhone(), removedPhone);
    }

    @Test
    public void addNewContactPositiveTest() {
        System.out.println("test 1");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        Assert.assertTrue(new ContactsPage(getDriver()).validateLastElementContactList(contactDto));
    }

    @Test
    public void addNewContactPositiveTest_noDescription() {
        contactDto.setDescription("");
        contactDto.setPhone("0000000001");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        if (addContactPage.isContactCard()) {
            String removedPhone = addContactPage.removeLastContact(contactDto);
            Assert.assertEquals(contactDto.getPhone(), removedPhone);
        } else {
            System.out.println("Test failed.");
            Assert.fail();
        }
    }

    @Test
    public void addNewContactPositiveTest_noEmail() {
        contactDto.setEmail("");
        contactDto.setPhone("0000000009");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        if (addContactPage.isContactCard()) {
            String removedPhone = new AddContactPage(getDriver()).removeLastContact(contactDto);
            Assert.assertEquals(contactDto.getPhone(), removedPhone);
        } else {
            System.out.println("Test failed.");
            Assert.fail();
        }
    }

    @Test
    public void addNewContactNegativeTest_noName() {
        contactDto.setName("");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        if (addContactPage.isSaveBtn()) {
            Assert.assertTrue(!addContactPage.isContactCard());
        } else {
            System.out.println("Test failed.");
            Assert.fail();
        }
    }

    @Test
    public void addNewContactNegativeTest_noLastName() {
        contactDto.setLastName("");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        Assert.assertTrue(!addContactPage.isContactCard());
    }

    @Test
    public void addNewContactNegativeTest_wrongEmail() {
        contactDto.setEmail("treegreenmail.com");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage
                .isAlertCorrect("Email not valid: must be a well-formed"));
    }

    @Test
    public void addNewContactNegativeTest_noPhone() {
        contactDto.setPhone("");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage
                .isAlertCorrect("Phone not valid: Phone number must"));
    }

    @Test
    public void addNewContactNegativeTest_wrongPhone() {
        contactDto.setPhone("12as567890");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage
                .isAlertCorrect("Phone not valid: Phone number must"));
    }

    @Test
    public void addNewContactNegativeTest_noAddress() {
        contactDto.setAddress("");
        addContactPage.typeContactData(contactDto);
        addContactPage.clickBtnSave();
        Assert.assertTrue(!addContactPage.isContactCard());
    }

    @Test(priority = 1)
    public void removeLastContactPositiveTest() {
        System.out.println("test 10");
        addContactPage.clickBtnContacts();
        if (addContactPage.isContactCard()) {
            String removedPhone = addContactPage.removeLastContact(contactDto);
            Assert.assertEquals(contactDto.getPhone(), removedPhone);
        } else {
            System.out.println("Do not run this case along!");
            Assert.fail("Perhaps this case run along.");
        }
    }

}
