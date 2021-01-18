package facades;

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

    public int getContactCount() {

        EntityManager em = emf.createEntityManager();
        try {
            long contactCount = (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            int contactCountInt = Math.toIntExact(contactCount);
            return contactCountInt;
        } finally {
            em.close();
        }
    }

}
