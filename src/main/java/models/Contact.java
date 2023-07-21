package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Contact {
    String name;
    String lastName;
    String phone;
    String email;
    String address;
    String description;
}
