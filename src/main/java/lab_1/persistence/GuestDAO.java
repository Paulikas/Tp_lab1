package lab_1.persistence;

import lab_1.entities.Guest;
import org.omg.CORBA.INTERNAL;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GuestDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em){this.em = em;}

    public void persist(Guest guest){ this.em.persist(guest);}

    public Guest findOne(Integer id){ return em.find(Guest.class, id);}

    public Guest findByName(String name){
        return (Guest) em.createQuery("select g from Guest as g "+
                "where g.name like concat('%',:guest_name,'%')")
                .setParameter("guest_name", name)
                .getSingleResult();
    }

    public Guest updateGuest(Guest guest){
        return em.merge(guest);
    }

    public List<Guest> loadAll(){
        return em.createQuery("select g from Guest as g", Guest.class).getResultList();
    }

    public List<Guest> findByHotel(Integer hotelId) {
        return em.createQuery("select g from Guest as g "+
                "join g.hotels as h "+
                "where h.id = :hotel_id")
                .setParameter("hotel_id", hotelId)
                .getResultList();
    }

    public void deleteById(Integer id){
        Guest guest = findOne(id);
        em.remove(guest);
        System.out.println("Object was deleted from database: ID = " + guest.getId().toString());
    }
}
