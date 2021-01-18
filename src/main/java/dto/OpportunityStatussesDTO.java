package dto;

import entities.Opportunity;
import java.util.ArrayList;
import java.util.List;

public class OpportunityStatussesDTO {

    List<OpportunityStatusDTO> all = new ArrayList();

    public OpportunityStatussesDTO(List<Opportunity> opStatus) {
        opStatus.forEach((o) -> {
            all.add(new OpportunityStatusDTO(o));
        });
    }

}
