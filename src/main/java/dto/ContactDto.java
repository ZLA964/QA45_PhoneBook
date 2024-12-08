package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString
@Builder
@Getter
@Setter

public class ContactDto {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;


    public List<String> getContactDetails(){
        return Arrays.asList(name, lastName, phone, email, address, description);
    }
}
