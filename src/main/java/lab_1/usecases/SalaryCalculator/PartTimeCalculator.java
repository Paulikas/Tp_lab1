package lab_1.usecases.SalaryCalculator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
public class PartTimeCalculator implements SalaryCalculator {

    @Override
    public Integer calculate() {
        System.out.println("Started calculating salary for part time");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ended salary calculation for part time");
        return 300;
    }
}
