package lab_1.usecases;

import lab_1.entities.Guest;
import lab_1.entities.Hotel;
import lab_1.persistence.GuestDAO;
import lab_1.persistence.HotelDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Model
@ViewScoped
public class GuestInfo implements Serializable {

    @Inject
    private GuestDAO guestDAO;

    @Inject
    private HotelDAO hotelDAO;

    @Getter
    private List<Hotel> hotels;

    @Getter @Setter
    private Integer hotelForUpdateId;

    @Getter
    private Guest guest;


    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer guestId = Integer.parseInt(requestParameters.get("guestId"));
        this.guest = guestDAO.findOne(guestId);
        loadHotels();
    }

    private void loadHotels(){
        this.hotels = this.guest.getHotels();
    }

    public List<Hotel> loadAvailableHotels(){
        List<Hotel> allHotels = hotelDAO.loadAll();
        allHotels.removeAll(this.hotels);
        return allHotels;
    }

    @Transactional
    public String addHotel(){
        System.out.println(hotelForUpdateId);
        Guest updatedGuest = this.guestDAO.findOne(guest.getId());
        List<Hotel> hotels = updatedGuest.getHotels();
        Hotel hotel = this.hotelDAO.findOne(hotelForUpdateId);
        hotels.add(hotel);
        updatedGuest.setHotels(hotels);
        return "guestInfo?faces-redirect=true&guestId=" + updatedGuest.getId();
    }
}
