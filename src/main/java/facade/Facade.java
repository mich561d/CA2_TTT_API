package facade;

import dto.CityInfoDTO;
import dto.CompanyDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Michael, Jesper
 */
public class Facade implements IFacade {

    EntityManagerFactory emf;

    public Facade() {
        this.emf = Persistence.createEntityManagerFactory("pu", null);
    }

    @Override
    public PhoneDTO getPhoneByNumber(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("PhoneDTO.findByNumber", PhoneDTO.class).setParameter("number", number).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO getPersonByPhone(PhoneDTO phone) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("PersonDTO.findByNumber", PersonDTO.class).setParameter("number", phone.getNumber()).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public CompanyDTO getCompanyByPhone(PhoneDTO phone) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("CompanyDTO.findByPhone", CompanyDTO.class).setParameter("number", phone.getNumber()).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public CompanyDTO getCompanyByCVR(int cvr) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("CompanyDTO.findByCVR", CompanyDTO.class).setParameter("cvr", cvr).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public HobbyDTO getHobbyByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("HobbyDTO.findByName", HobbyDTO.class).setParameter("name", name).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO> getAllPersonsByHobby(HobbyDTO hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("PersonDTO.findAllByHobby", PersonDTO.class).setParameter("hobby", hobby.getId()).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfoDTO getCityByZip(String zip) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("CityInfoDTO.findByZipCode", CityInfoDTO.class).setParameter("zipCode", zip).getSingleResult();
        } finally {
            em.close();
        }
    }

    // PLEASE FIX ME
    @Override
    public List<PersonDTO> getAllPersonsByCity(CityInfoDTO city) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // PLEASE FIX ME
    @Override
    public List<String> getAllZipCodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // PLEASE FIX ME
    @Override
    public List<CompanyDTO> getAllCompaniesWithMoreEmployeesThan(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
