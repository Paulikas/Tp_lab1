package lab_1.persistence;

import lab_1.entities.Hotel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class HotelDAO {

    @Inject
    private EntityManager em;

    public List<Hotel> loadAll(){
        return em.createNamedQuery("Hotel.findAll", Hotel.class).getResultList();
    }

    public void setEm(EntityManager em){this.em = em;}

    public void persist(Hotel hotel){ this.em.persist(hotel);}

    public Hotel findOne(Integer id){return em.find(Hotel.class, id);}
}
