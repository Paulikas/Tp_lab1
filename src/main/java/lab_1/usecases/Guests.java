package lab_1.usecases;

import lab_1.entities.Guest;
import lab_1.entities.Hotel;
import lab_1.persistence.GuestDAO;
import lab_1.persistence.HotelDAO;
import lombok.Getter;
import lombok.Setter;

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
    private Integer hotelId=null;

    public List<Guest> loadGuestsByHotel(Integer hotelId){
        return guestDAO.findByHotel(hotelId);
    }

    @Transactional
    public String createGuest(){
        Hotel hotel = this.hotelDAO.findOne(hotelId);
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel);
        guestToCreate.setHotels(hotels);
        this.guestDAO.persist(guestToCreate);
        return "index?faces-redirect=true";
    }

    @Transactional
    public String updateGuest(){
        Guest guest = this.guestDAO.findByName(guestToCreate.getName());
        List<Hotel> hotels = guest.getHotels();
        Hotel hotel = this.hotelDAO.findOne(hotelId);
        hotels.add(hotel);
        guestToCreate.setHotels(hotels);
        return "index?faces-redirect=true";
    }
}
