package facades;

import dto.ContactDTO;
import dto.PersonDTO;
import entities.Contact;
import errorhandling.MissingInputException;
import errorhandling.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ContactFacade {

    private static EntityManagerFactory emf;
    private static ContactFacade instance;

    private ContactFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static ContactFacade getContactFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ContactFacade();
        }
        return instance;
    }

    public long getContactCount() {

        EntityManager em = emf.createEntityManager();
        try {
            long contactCount = (long) em.createQuery("SELECT COUNT(p) FROM Contact p").getSingleResult();
            return contactCount;
        } finally {
            em.close();
        }
    }

    public ContactDTO addContact(ContactDTO contact) throws NotFoundException {

        EntityManager em = emf.createEntityManager();

        String name = contact.getName();
        String email = contact.getEmail();
        String company = contact.getCompany();
        String jobTitle = contact.getJobTitle();
        String phone = contact.getPhone();

        Contact newContact;

        newContact = em.find(Contact.class, email);

        if (newContact != null) {
            throw new NotFoundException("Contact already exists");
        } else {
            try {
                if (email.length() > 0) {
                    newContact = new Contact(email, name, company, jobTitle, phone);
                    em.getTransaction().begin();
                    em.persist(newContact);
                    em.getTransaction().commit();
                }
            } finally {
                em.close();
            }
        }
        return new ContactDTO(newContact);
    }
}
