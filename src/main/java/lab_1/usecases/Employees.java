package lab_1.usecases;



import lab_1.entities.Employee;
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
    private EmployeeDAO employeeDAO;

    @Inject
    private HotelDAO hotelDAO;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @Getter @Setter
    private Integer employeeToDeleteId;

    @Getter @Setter
    private Integer hotelId = null;


    public List<Employee> loadAllEmployees(){ return employeeDAO.FindAll();}

    @Transactional
    public String deleteEmployee(){
        employeeDAO.deleteById(this.employeeToDeleteId);
        return "employees?faces-redirect=true";
    }
}
