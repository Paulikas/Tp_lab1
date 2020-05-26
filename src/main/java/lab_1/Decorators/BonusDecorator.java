package lab_1.Decorators;

import lab_1.usecases.SalaryCalculator.SalaryCalculator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class BonusDecorator implements SalaryCalculator {

    @Inject @Delegate @Any
    SalaryCalculator salaryCalculator;

    @Override
    public Integer calculate() {
        System.out.println("Additionaly calculating for bonus");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        return salaryCalculator.calculate() +200;
    }
}
