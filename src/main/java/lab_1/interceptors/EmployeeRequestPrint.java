package lab_1.interceptors;

import lab_1.REST.Responses.EmployeeResponse;

import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@RequestInter
public class EmployeeRequestPrint implements Serializable {
    @AroundInvoke
    public Object printRequestContent(InvocationContext context) throws Exception {
            EmployeeResponse result = (EmployeeResponse) context.proceed();
            System.out.println("Request: ID: " + result.getId() + " Name: " + result.getName() + " HotelId: " + result.getHotelId());
            return result;
    }
}
