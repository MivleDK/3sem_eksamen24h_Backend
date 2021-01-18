package dto;

import entities.Contact;
import entities.Opportunity;

public class OpportunityDTO {

    private String name;
    private double amount;
    private String closeDate;
    private Contact contact;
    private String opStatus;
    

    public OpportunityDTO(Opportunity opportunity) {
        this.name = opportunity.getName();
        this.amount = opportunity.getAmount();
        this.closeDate = opportunity.getCloseDate();
        this.contact = opportunity.getContact();
        //this.opStatus = opportunity.getStatus().getStatusName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
