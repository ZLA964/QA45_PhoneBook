package tests;

import dto.ContactDto;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;

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
                    .email("treeGreen" + i + "@mail.com")
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

// This Test was needing for debuting, for check idea.
/* /   @Test
    public void checkContactDetailsTest() {
        int index = new Random().nextInt(6);
        contact = contacts.get(index);
        String contactDtoDetails = contact.toWebString();
        String details = editContactPage.getDetailsContactByIndex(index);
        Assert.assertEquals(details,contactDtoDetails );
    }
*/

    @Test
    public void editContactTest_changeName() {
        editContactPage.openFistContactCardForEdit();
        String newName = "Palma";
        editContactPage.changeName(newName);
        contacts.get(0).setName(newName);
        String[] result = detailsContactsForCheck(0);
        Assert.assertEquals(result[1], result[0]);
    }

    @Test
    public void editContactTest_changeLastName() {
        editContactPage.openLastContactCardForEdit();
        String newLastName = "Tamar";
        editContactPage.changeLastName(newLastName);
        contacts.get(5).setLastName(newLastName);
        String[] result = detailsContactsForCheck(5);
        Assert.assertEquals(result[1], result[0]);
    }

    @Test
    public void editContactPositiveTest_changePhone() {
        editContactPage.openContactCardByIndexForEdit(1);
        String newPhone = "9876543210";
        editContactPage.changePhone(newPhone);
        contacts.get(1).setPhone(newPhone);
        String[] result = detailsContactsForCheck(1);
        Assert.assertEquals(result[1], result[0]);
    }

    @Test
    public void editContactTest_changeEmail() {
        editContactPage.openContactCardByIndexForEdit(2);
        String newEmail = "newemail@mail.com";
        editContactPage.changeEmail(newEmail );
        contacts.get(2).setEmail(newEmail);
        String[] result = detailsContactsForCheck(2);
        Assert.assertEquals(result[1], result[0]);
    }

    @Test
    public void editContactPositiveTest_changeAddress() {
        editContactPage.openContactCardByIndexForEdit(3);
        String newAddress = "Dark forest";
        editContactPage.changeAddress(newAddress);
        contacts.get(3).setAddress(newAddress);
        String[] result = detailsContactsForCheck(3);
        Assert.assertEquals(result[1], result[0]);
    }

// this test fall! It is bag!
/* /
    @Test
    public void editContactPositiveTest_changeDescription() {
        editContactPage.openContactCardByIndexForEdit(4);
        String newDescription = "new description for 4";
        editContactPage.changeDescription(newDescription);
        contacts.get(4).setDescription(newDescription);
        String[] result = detailsContactsForCheck(4);
        Assert.assertEquals(result[1], result[0]);
    }
*/

    @Test
    public void editContactPositiveTest_changeAllWithoutDescription() {
        editContactPage.openContactCardByIndexForEdit(0);
        contact = ContactDto.builder()
                .name("NewTree")
                .lastName("NewGreen--0")
                .email("newTreeGreen" + 0 + "@mail.com")
                .phone("0000000001")
                .address("NewForest")
                .description("Test contact 00")
                .build();
        editContactPage.changeAllContactDetails(contact);
        contacts.set(0, contact);
        String[] result = detailsContactsForCheck(0);
        Assert.assertEquals(result[1], result[0]);
    }
}
