package lab_1.usecases;

import lab_1.interceptors.LoggedInvocation;
import lab_1.usecases.SalaryCalculator.SalaryCalculator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class CalculateSalary implements Serializable{
    @Inject
    private SalaryCalculator salaryCalculator;

    private Map<Integer, CompletableFuture<Integer>> salaryStatuses = new HashMap<>();


    @LoggedInvocation
    public String calculateSalary() throws ExecutionException, InterruptedException{

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer employeeId = Integer.parseInt(requestParameters.get("employeeId"));

        if (!salaryStatuses.containsKey(employeeId) || salaryStatuses.get(employeeId).isDone()) {
            salaryStatuses.put(employeeId, CompletableFuture.supplyAsync(() -> salaryCalculator.calculate()));
        }

        return "employeeInfo?faces-redirect=true&employeeId=" + employeeId;
    }


    @LoggedInvocation
    public String getSalaryStatus(Integer employeeId) throws ExecutionException, InterruptedException{
        if (!salaryStatuses.containsKey(employeeId) || salaryStatuses.get(employeeId) == null) {
            return "Calculation not requested";
        } else if (salaryStatuses.get(employeeId).isDone()) {
            return "Expected salary " + salaryStatuses.get(employeeId).get().toString();
        } else {
            return "Calculation in progress";
        }
    }
}
