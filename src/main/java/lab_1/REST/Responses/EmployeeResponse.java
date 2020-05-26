package lab_1.REST.Responses;

import lab_1.entities.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeResponse {

    public EmployeeResponse(){}

    public EmployeeResponse(Integer id, String name, Integer hotelId){
        this.Id = id;
        this.Name = name;
        this.HotelId = hotelId;
    }

    private Integer Id;

    private String Name;

    private Integer HotelId;
}
