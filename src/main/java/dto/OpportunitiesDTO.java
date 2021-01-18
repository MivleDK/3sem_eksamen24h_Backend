package dto;

import entities.Opportunity;
import java.util.ArrayList;
import java.util.List;

public class OpportunitiesDTO {

    List<OpportunityDTO> all = new ArrayList<>();

    public OpportunitiesDTO(List<Opportunity> opportunities) {
        opportunities.forEach((o) -> {
            all.add(new OpportunityDTO(o));
        });
    }

    public List<OpportunityDTO> getAll() {
        return all;
    }

    public void setAll(List<OpportunityDTO> all) {
        this.all = all;
    }
}
