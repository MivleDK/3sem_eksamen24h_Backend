package dto;

import entities.Contact;
import java.util.List;

public class ContactDTO {

    private String email;
    private String name;
    private String company;
    private String jobTitle;
    private String phone;

    private List<String> opportunities;

    public ContactDTO(Contact contact) {
        this.email = contact.getEmail();
        this.name = contact.getName();
        this.company = contact.getCompany();
        this.jobTitle = contact.getJobTitle();
        this.phone = contact.getPhone();
    }

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
