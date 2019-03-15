package facade;

import dto.PhoneDTO;
import entity.Phone;
import interfaces.IPhone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class FPhone implements IPhone {

    EntityManagerFactory emf;

    public FPhone(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public PhoneDTO getPhoneByNumberRaw(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("PhoneDTO.findByNumber", PhoneDTO.class).setParameter("number", number).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Phone getPhoneByNumber(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Phone.findByNumber", Phone.class).setParameter("number", number).getSingleResult();
        } finally {
            em.close();
        }
    }

}
