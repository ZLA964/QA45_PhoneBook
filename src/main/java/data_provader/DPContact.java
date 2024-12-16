package data_provader;

import dto.ContactDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.RandomUtils.*;
import static utils.RandomUtils.generateString;

public class DPContact {

    @DataProvider
    public ContactDtoLombok[] newContactsDP() {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .phone(generatePhone(10))
                .address("Address " + generateString(14))
                //               .description("test")
                .build();
        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .phone(generatePhone(10))
                .address("Address " + generateString(14))
                //               .description("test")
                .build();
        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .phone(generatePhone(10))
                .address("Address " + generateString(14))
                //               .description("test")
                .build();
        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public Iterator<ContactDtoLombok> newContactDPFile() {
        List<ContactDtoLombok> contactList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(
                    "src/main/resources/data_provider/testContactsData .csv"));

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                contactList.add(ContactDtoLombok.builder()
                        .name(splitArray[0])
                        .lastName(splitArray[1])
                        .phone(splitArray[2])
                        .email(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contactList.listIterator();
    }


}
