package tests;

import dto.ContactDto;
import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;

public class EditContactsTests extends ApplicationManager {
    UserDto user = new UserDto( "frodo_begin" + 1 + "@gmail.com", "P1password!_");
    ContactsPage contactsPage;
    AddContactPage addContactPage;
    EditContactPage editContactPage;
    ArrayList<ContactDto> contacts = new ArrayList<>(6);

    ContactDto contact;

    private void  deleteAllContacts(){
        int size = contactsPage.quantityContacts();
        for (int i = 0 ; i < size; i++){
            contactsPage.deleteFirstContact();
        }
    }

    @BeforeMethod
    public void login() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
        deleteAllContacts();
        // add new 6 contacts
        for(int i = 0 ; i < 6 ; i++){
            contactsPage.clickBtnAdd();
            addContactPage = new AddContactPage(getDriver());
            contact = ContactDto.builder()
                    .name("Tree")
                    .lastName("Green--0")
                    .email("treegreen" +i+ "@mail.com")
                    .phone(i+"023456789")
                    .address("Forest")
                    .description("Test contact 0"+i)
                    .build();
            addContactPage.addNewContact(contact);
            contacts.add(contact);
        };
    }

    @Test
    public void editContactTest_changeName(){
//        editContactPage.openFistContactCardForEdit();;
    }

    @Test
    public void editContactTest_changeLastName(){
        editContactPage.openLastContactCardForEdit();
    }

    @Test
    public void editContactTest_changeEmail(){
        editContactPage.openContactCardByIndexForEdit(4);;
    }

    @Test
    public void checkContactDetailsTest(){
        contact.setName("Tree--2");
        String contactDtoDetails = contact.toWebString();
        String details = editContactPage.getDetailsContactByIndex(2);
//        System.out.println(details);
//        System.out.println("==== contactDto =====");
//        System.out.println(contactDtoDetails);
//        Boolean check = contactDtoDetails.equals(details);
//        System.out.println("check--> " + check);
        Assert.assertEquals(contactDtoDetails, details);
    }



}
