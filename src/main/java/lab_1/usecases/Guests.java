package lab_1.usecases;

import lab_1.entities.Guest;
import lab_1.entities.Hotel;
import lab_1.persistence.GuestDAO;
import lab_1.persistence.HotelDAO;
import lombok.Getter;
import lombok.Setter;
import sun.plugin.javascript.navig.LinkArray;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class Guests {

    @Inject
    private GuestDAO guestDAO;

    @Inject
    private HotelDAO hotelDAO;

    @Getter @Setter
    private Guest guestToCreate = new Guest();

    @Getter @Setter
    private Integer guestToDeleteId;

    @Getter @Setter
    private List<Guest> guests;

    @Getter @Setter
    private Integer hotelId=null;

    @Getter
    private List<Hotel> hotels;

    public List<Guest> loadGuestsByHotel(Integer hotelId){
        return guestDAO.findByHotel(hotelId);
    }

    public List<Guest> loadAllGuests(){
        return guestDAO.loadAll();
    }

    @PostConstruct
    private void init() {
        guests = loadAllGuests();
    }

    @Transactional
    public String createGuest(){
        this.guestDAO.persist(guestToCreate);
        return "Guests?faces-redirect=true";
    }


    @Transactional
    public String deleteGuest(){
        this.guestDAO.deleteById(guestToDeleteId);
        return "Guests?faces-redirect=true";
    }
}
