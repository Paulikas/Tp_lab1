package lab_1.usecases.SalaryCalculator;

import lab_1.interceptors.LoggedInvocation;

import java.io.Serializable;

public interface SalaryCalculator extends Serializable {
    @LoggedInvocation
    public abstract Integer calculate();
}
