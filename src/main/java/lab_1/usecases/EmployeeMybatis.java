package lab_1.usecases;

import lab_1.MyBatis.Model.Employee;
import lab_1.MyBatis.Model.Hotel;
import lab_1.MyBatis.dao.EmployeeMapper;
import lab_1.MyBatis.dao.HotelMapper;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class EmployeeMybatis {

    @Inject
    private EmployeeMapper employeeMapper;

    @Inject
    private HotelMapper hotelMapper;

    @Getter @Setter
    private Employee employee;

    @Getter @Setter
    private Hotel hotel;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer employeeId = Integer.parseInt(requestParameters.get("employeeId"));
        this.employee = employeeMapper.selectByPrimaryKey(employeeId);
        loadHotel();
    }
    private void loadHotel(){
       this.hotel = hotelMapper.selectByPrimaryKey(employee.getHotelId());
    }

    @Transactional
    public String deleteEmployee(){
        employeeMapper.deleteByPrimaryKey(employee.getId());
        return "employees?faces-redirect=true";
    }
}
