package facades;

import dto.ContactDTO;
import dto.ContactsDTO;
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

    public ContactsDTO getAllContacts() throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        ContactsDTO cDTO;

        try {
            cDTO = new ContactsDTO(em.createQuery("SELECT p FROM Contact p").getResultList());
        } catch (Exception e) {
            throw new NotFoundException("No connection to the database");
        } finally {
            em.close();
        }
        return cDTO;
    }

    public ContactDTO getContact(String email) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
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

    public String editContact(ContactDTO cDTO) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Contact contact = em.find(Contact.class, cDTO.getEmail());
        if (contact == null) {
            throw new NotFoundException("No contact with that email found");
        }
        contact.setName(cDTO.getName());
        contact.setCompany(cDTO.getCompany());
        contact.setJobTitle(cDTO.getJobTitle());
        contact.setPhone(cDTO.getPhone());

        try {
            em.getTransaction().begin();
            em.merge(contact);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "Update OK";
    }

    public String deleteContact(ContactDTO cDTO) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Contact contact = em.find(Contact.class, cDTO.getEmail());
        if (contact == null) {
            throw new NotFoundException("No contact with that email found");
        }

        try {
            em.getTransaction().begin();
            em.remove(contact);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "Contact \"" + contact.getName() + "\" was deleted";
    }

}
