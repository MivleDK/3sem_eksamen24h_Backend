package dto;

import entities.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactsDTO {

    List<ContactDTO> all = new ArrayList<>();

    public ContactsDTO(List<Contact> contacts) {
        contacts.forEach((p) -> {
            all.add(new ContactDTO(p));
        });
    }

    public List<ContactDTO> getAll() {
        return all;
    }

    public void setAll(List<ContactDTO> all) {
        this.all = all;
    }

}
