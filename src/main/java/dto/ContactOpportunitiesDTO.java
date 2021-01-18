package dto;

import entities.Contact;
import entities.Opportunity;
import java.util.ArrayList;
import java.util.List;

public class ContactOpportunitiesDTO {

    private String email;
    private String name;
    private String company;
    private String jobTitle;
    private String phone;

    private List<OpportunityDTO> all = new ArrayList<>();

    public ContactOpportunitiesDTO(List<Opportunity> opportunity, Contact contact) {
        this.email = contact.getEmail();
        this.name = contact.getName();
        this.company = contact.getCompany();
        this.jobTitle = contact.getJobTitle();
        this.phone = contact.getPhone();
        opportunity.forEach((o -> {
            all.add(new OpportunityDTO(o));
        }));
    }

}
