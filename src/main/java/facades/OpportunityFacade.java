package facades;

import dto.ContactDTO;
import dto.ContactOpportunitiesDTO;
import dto.OpportunityDTO;
import entities.Contact;
import entities.Opportunity;
import errorhandling.NotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class OpportunityFacade {

    private static EntityManagerFactory emf;
    private static OpportunityFacade instance;

    public OpportunityFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static OpportunityFacade getOpportunityFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OpportunityFacade();
        }
        return instance;
    }

    public long getOpportunityCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long opCount = (long) em.createQuery("SELECT COUNT(o) FROM Opportunity o").getSingleResult();
            return opCount;
        } finally {
            em.close();
        }
    }

    public String addOpportunity(OpportunityDTO opDTO, String email) throws NotFoundException, Exception {
        EntityManager em = emf.createEntityManager();

        String name = opDTO.getName();
        double amount = opDTO.getAmount();
        String closeDate = opDTO.getCloseDate();

        Opportunity newOp = new Opportunity(name, amount, closeDate);
        Contact contact = em.find(Contact.class, email);

        if (contact == null) {
            throw new NotFoundException("No contact with that email found");
        }

        TypedQuery<Opportunity> queryList = em.createQuery("SELECT o FROM Opportunity o", Opportunity.class);
        List<Opportunity> opportunityList = queryList.getResultList();

        for (Opportunity opportunity : opportunityList) {
            if (opportunity.getName().equalsIgnoreCase(opDTO.getName()) && opportunity.getCloseDate().equalsIgnoreCase(opDTO.getCloseDate())) {
                throw new NotFoundException("Opportunity has already been added to this contact");
            }

        }

        try {
            contact.addOpportunity(newOp);
            em.getTransaction().begin();
            em.merge(contact);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "Opportunity was added to: " + contact.getName();
    }

    public ContactDTO getOpportunityByEmail(String email) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        ContactOpportunitiesDTO coDTO;
        Contact contact;

        try {
            contact = em.find(Contact.class, email);
            if (contact == null) {
                throw new NotFoundException("No contact with that email found");
            }
        } finally {
            em.close();
        }
        return new ContactDTO(contact);
    }

}
