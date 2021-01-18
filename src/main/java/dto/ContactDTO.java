package dto;

import entities.Contact;
import entities.Opportunity;
import java.util.ArrayList;
import java.util.List;

public class ContactDTO {

    private String email;
    private String name;
    private String company;
    private String jobTitle;
    private String phone;

    private List<String> opportunities;
  //private List<OpportunityDTO> opportunities2 = new ArrayList<>();

    public ContactDTO(Contact contact) {
        this.email = contact.getEmail();
        this.name = contact.getName();
        this.company = contact.getCompany();
        this.jobTitle = contact.getJobTitle();
        this.phone = contact.getPhone();
        this.opportunities = contact.getOpportunitiesAsStringList();
        //makeOpportunityDTOList(contact);

    }

//    public void makeOpportunityDTOList(Contact contact) {
//        List<Opportunity> opportunities = contact.getOpportunityList();
//        opportunities.forEach((o -> {
//            this.opportunities2.add(new OpportunityDTO(o));
//        }));
//    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<String> opportunities) {
        this.opportunities = opportunities;
    }

}
