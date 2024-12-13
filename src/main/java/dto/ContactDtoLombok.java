package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ContactDtoLombok {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;

    @Override
    public String toString() {
        return "ContactDtoLombok{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String toWebString() {
        return  name + ' ' + lastName + '\n' +
                phone + '\n' +
                email + '\n' +
                address + '\n' +
                "Description: " + description + '\n' +
                "EditRemove";
    }
}
