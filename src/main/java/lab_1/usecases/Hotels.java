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
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Hotels {

    @Inject
    private HotelDAO hotelDAO;


    @Getter @Setter
    private Hotel hotelToCreate = new Hotel();


    @Getter
    private List<Hotel> hotels;



    private void loadHotels(){this.hotels = hotelDAO.loadAll();}



    @PostConstruct
    public void init(){loadHotels();}

    @Transactional
    public String createHotel(){
        this.hotelDAO.persist(hotelToCreate);
        return "Hotels?faces-redirect=true";
    }
}
