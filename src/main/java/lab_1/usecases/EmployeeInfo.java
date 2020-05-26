package lab_1.usecases;


import lab_1.entities.Employee;
import lab_1.entities.Hotel;
import lab_1.persistence.EmployeeDAO;
import lab_1.persistence.HotelDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Model
@ViewScoped
public class EmployeeInfo implements Serializable {

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private HotelDAO hotelDAO;

    @Getter @Setter
    private Employee employee;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private String empType;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer employeeId = Integer.parseInt(requestParameters.get("employeeId"));
        this.employee = employeeDAO.findOne(employeeId);
        loadHotel();
    }
    public List<String> getTypes()
    {
        List<String> types = Arrays.asList("Working", "On Vacation");
        return types;
    }

    private void loadHotel() {
        this.hotel = hotelDAO.findOne(employee.getHotel().getId());
    }

    @Transactional
    public String setEmployment(){
        Employee updatedEmployee = employeeDAO.findOne(employee.getId());
        updatedEmployee.setEmploymentType(empType);
        return "employeeInfo?faces-redirect=true&employeeId="+updatedEmployee.getId();
    }


}
