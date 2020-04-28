package lab_1.usecases;

import lab_1.entities.Employee;
import lab_1.entities.Guest;
import lab_1.entities.Hotel;
import lab_1.persistence.EmployeeDAO;
import lab_1.persistence.GuestDAO;
import lab_1.persistence.HotelDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class HotelInfo implements Serializable {

    @Inject
    private HotelDAO hotelDAO;

    @Inject
    private EmployeeDAO empDAO;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private List<Guest> guests;

    @Getter @Setter
    private List<Employee> employees;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer hotelId = Integer.parseInt(requestParameters.get("hotelId"));
        this.hotel = hotelDAO.findOne(hotelId);
        this.guests = hotel.getGuests();
        this.employees = hotel.getEmployees();
    }

    public void loadEmployees(){
        this.employees = empDAO.FindByHotel(hotel.getId());
    }

    @Transactional
    public String createEmployee(){
        System.out.println(employeeToCreate.getFullName());
        employeeToCreate.setHotel(hotel);
        this.empDAO.persist(employeeToCreate);
        return "hotelInfo?faces-redirect=true&hotelId="+hotel.getId();
    }

    @Transactional
    public String deleteHotel(){
        this.hotelDAO.deleteById(hotel.getId());
        return "Hotels?faces-redirect=true";
    }

}
