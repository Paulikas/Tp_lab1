package lab_1.REST;

import lab_1.REST.Requests.EmployeeRequest;
import lab_1.REST.Responses.EmployeeResponse;
import lab_1.entities.Employee;
import lab_1.entities.Hotel;
import lab_1.interceptors.RequestInter;
import lab_1.persistence.EmployeeDAO;
import lab_1.persistence.HotelDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestController {

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private HotelDAO hotelDAO;

    //@RequestInter
    @Path("/")
    @GET
    public List<EmployeeResponse> getAll(){
        List<Employee> employees = employeeDAO.FindAll();

        List<EmployeeResponse> employeeResponseList = new ArrayList<>();

        for(Employee employee: employees){
            EmployeeResponse employeeResponse = new EmployeeResponse(
                    employee.getId(), employee.getFullName(), employee.getHotel().getId());

            /*employeeResponse.setId(employee.getId());
            employeeResponse.setName(employee.getFullName());
            employeeResponse.setHotelId(employee.getHotel().getId());*/

            employeeResponseList.add(employeeResponse);
        }

        return employeeResponseList;
    }

    @RequestInter
    @Path("/{employeeId}")
    @GET
    public EmployeeResponse getById(@PathParam("employeeId") Integer id){
        Employee employee = employeeDAO.findOne(id);

        EmployeeResponse employeeResponse = new EmployeeResponse(
                employee.getId(), employee.getFullName(), employee.getHotel().getId());

        return employeeResponse;
    }

    //@RequestInter
    @Path("/{employeeId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public EmployeeResponse update(EmployeeRequest emp, @PathParam("employeeId") Integer id){
        Employee employee = employeeDAO.findOne(id);

        if (employee == null){
            throw new IllegalArgumentException("Employee with the id "+id+" not found");
        }

        Hotel hotel = hotelDAO.findOne(emp.getHotelId());
        employee.setHotel(hotel);

        employee.setFullName(emp.getName());
        employee.setId(emp.getId());

        EmployeeResponse employeeResponse = new EmployeeResponse(
                employee.getId(), employee.getFullName(), employee.getHotel().getId());

        return employeeResponse;
    }

    @RequestInter
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public EmployeeResponse create(EmployeeRequest emp){
        Employee employee = new Employee();


        if(emp.getHotelId() != null) {
            Hotel hotel = hotelDAO.findOne(emp.getHotelId());

            if (hotel == null) {
                throw new IllegalArgumentException("No such hotel exists");
            }else
                employee.setHotel(hotel);
        }else
            throw new IllegalArgumentException("No hotel id was provided");

        employee.setFullName(emp.getName());
        employeeDAO.persist(employee);

        EmployeeResponse employeeResponse = new EmployeeResponse(
                employee.getId(), employee.getFullName(), employee.getHotel().getId());

        return employeeResponse;
    }
}
