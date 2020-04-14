package lab_1.persistence;

import lab_1.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class EmployeeDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em){this.em = em;}

    public void persist(Employee employee){this.em.persist(employee);}

    public Employee findOne(Integer id) { return em.find(Employee.class, id);}

    public List<Employee> FindByHotel(Integer hotelId){
        return em.createQuery(
                "select e from Employee e "+
                        "where e.hotel.id = :hotel_id")
                .setParameter("hotel_id", hotelId)
                .getResultList();

    }
}
