package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contacts")
@NamedQuery(name = "Contacts.deleteAllRows", query = "DELETE from Contact")
public class Contact implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "email", length = 50)
    private String email;
    
    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "company", length = 100)
    private String company;
    
    @Column(name = "jobtitle", length = 250)
    private String jobTitle;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contact")
    private List<Opportunity> opportunityList = new ArrayList<>();
    
    public Contact() {
    }
    
    public Contact(String email, String name, String company, String jobTitle, String phone) {
        this.email = email;
        this.name = name;
        this.company = company;
        this.jobTitle = jobTitle;
        this.phone = phone;
    }
    
    public void addOpportunity(Opportunity op) {
        if (op != null) {
            opportunityList.add(op);
            op.setContact(this);
        }
    }
    
    public List<Opportunity> getOpportunityList() {
        return opportunityList;
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
}
