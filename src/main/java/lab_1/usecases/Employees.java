package lab_1.usecases;

import lab_1.MyBatis.Model.Employee;
import lab_1.MyBatis.dao.EmployeeMapper;

import lab_1.entities.Hotel;
import lab_1.persistence.EmployeeDAO;
import lab_1.persistence.HotelDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Employees {

    @Inject
    private EmployeeMapper employeeMapper;

    @Inject
    private HotelDAO hotelDAO;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @Getter @Setter
    private Integer employeeToDeleteId;

    @Getter @Setter
    private Integer hotelId = null;


    public List<Employee> loadAllEmployees(){ return employeeMapper.selectAll();}

    @Transactional
    public String deleteEmployee(){
        employeeMapper.deleteByPrimaryKey(this.employeeToDeleteId);
        return "employees?faces-redirect=true";
    }
}
