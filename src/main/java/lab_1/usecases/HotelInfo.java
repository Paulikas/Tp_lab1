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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
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

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer hotelId = Integer.parseInt(requestParameters.get("hotelId"));
        this.hotel = hotelDAO.findOne(hotelId);
        this.guests = hotel.getGuests();
        this.employees = hotel.getEmployees();
    }

    public String doStuff(){
        System.out.println("Susikuriau");
        return "susikuriau";
    }

    public void loadEmployees(){
        this.employees = empDAO.FindByHotel(hotel.getId());
    }

}
