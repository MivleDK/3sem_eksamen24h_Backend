package dto;

import entities.Contact;
import entities.Opportunity;

public class OpportunityStatusDTO {

    private String status;
    private String name;
    private String contact;

    public OpportunityStatusDTO(Opportunity opportunity) {
        this.status = opportunity.getStatus().getStatusName();
        this.name = opportunity.getName();
        this.contact = opportunity.getContact().getName();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
