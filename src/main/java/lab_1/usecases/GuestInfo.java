package lab_1.usecases;

import lab_1.entities.Guest;
import lab_1.entities.Hotel;
import lab_1.persistence.GuestDAO;
import lombok.Getter;

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
public class GuestInfo implements Serializable {

    @Inject
    private GuestDAO guestDAO;

    @Getter
    private List<Hotel> hotels;

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
}
