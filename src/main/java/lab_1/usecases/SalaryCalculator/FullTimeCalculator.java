package lab_1.usecases.SalaryCalculator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Specializes
public class FullTimeCalculator extends PartTimeCalculator {

    @Override
    public Integer calculate() {
        System.out.println("Started calculating salary for full time");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ended salary calculation for full time");
        return super.calculate() * 2;
    }
}
