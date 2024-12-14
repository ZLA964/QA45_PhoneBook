package tests;

import dto.ContactDto;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;
import java.util.Random;

public class EditContactsTests extends ApplicationManager {
    UserDto user = new UserDto("frodo_begin" + 1 + "@gmail.com", "P1password!_");
    ContactsPage contactsPage;
    AddContactPage addContactPage;
    EditContactPage editContactPage;
    ArrayList<ContactDto> contacts = new ArrayList<>(6);
    boolean isSixContacts = false;

    ContactDto contact;

    private void deleteAllContacts() {
        int size = contactsPage.quantityContacts();
        for (int i = 0; i < size; i++) {
            contactsPage.deleteFirstContact();
        }
    }

    private void addSixTestContacts() {
        for (int i = 0; i < 6; i++) {
            contactsPage.clickBtnAdd();
            addContactPage = new AddContactPage(getDriver());
            contact = ContactDto.builder()
                    .name("Tree")
                    .lastName("Green--0" + i)
                    .email("treegreen" + i + "@mail.com")
                    .phone(i + "023456789")
                    .address("Forest")
                    .description("Test contact 0" + i)
                    .build();
            addContactPage.addNewContact(contact);
            contacts.add(contact);
        }
    }

    private String[] detailsContactsForCheck(int index) {
        String contactDtoDetails = contacts.get(index).toWebString();
        String details = editContactPage.getDetailsContactByIndex(index);
        return  new String[]{contactDtoDetails ,details};
    }

    @BeforeMethod
    public void login() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
        if (!isSixContacts) {
            deleteAllContacts();           // clear contacts page
            addSixTestContacts();         // add new 6 contacts for change tests
            isSixContacts = true;
        }
        addContactPage.clickBtnContacts();
        editContactPage = new EditContactPage(getDriver());  // take the testing page
    }


    @Test
    public void checkContactDetailsTest() {
        int index = new Random().nextInt(6);
        contact = contacts.get(index);
        String contactDtoDetails = contact.toWebString();
        String details = editContactPage.getDetailsContactByIndex(index);
//        System.out.println(details);
//        System.out.println("==== contactDto =====");
//        System.out.println(contactDtoDetails);
//        boolean check = contactDtoDetails.equals(details);
//        System.out.println("check--> " + check);
        Assert.assertEquals(details,contactDtoDetails );
    }


    @Test
    public void editContactTest_changeName() {
        editContactPage.openFistContactCardForEdit();
        String newName = "Palma";
        editContactPage.changeContactName(newName);
        contacts.get(0).setName(newName);
        String[] result = detailsContactsForCheck(0);
        Assert.assertEquals(result[1], result[0]);
    }

    @Test
    public void editContactTest_changeLastName() {
        editContactPage.openLastContactCardForEdit();
    }

    @Test
    public void editContactTest_changeEmail() {
        editContactPage.openContactCardByIndexForEdit(4);
    }


}
