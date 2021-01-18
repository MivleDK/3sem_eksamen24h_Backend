package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "opportunity")
@NamedQuery(name = "Opportunity.deleteAllRows", query = "DELETE FROM Opportunity")
public class Opportunity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "amount")
    private double amount;

    @Column(name = "close_date")
    private String closeDate;
    //ATTENTION: DATE FORMAT IS (YEAR - 1900, MONTH (0-12), DAY - 1

    @JoinColumn(name = "contact_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Contact contact;
    
    @JoinColumn(name = "status_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private OpportunityStatus status;

    public Opportunity() {
    }

    public Opportunity(String name, double amount, String closeDate) {
        this.name = name;
        this.amount = amount;
        this.closeDate = closeDate;
    }

    public OpportunityStatus getStatus() {
        return status;
    }

    public void setStatus(OpportunityStatus status) {
        this.status = status;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public Long getId() {
        return id;
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

}
