package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "opportunity_status")
@NamedQuery(name = "OpportunityStatus.deleteAllRows", query = "DELETE FROM OpportunityStatus")
public class OpportunityStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "status")
    private List<Opportunity> opportunityList = new ArrayList<>();

    private String statusName;

    public OpportunityStatus() {
    }

    public OpportunityStatus(String status) {
        this.statusName = status;
    }

    public void addOpportunity(Opportunity opportunity) {
        if (opportunity != null) {
            opportunityList.add(opportunity);
            opportunity.setStatus(this);
        }
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public Long getId() {
        return id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
