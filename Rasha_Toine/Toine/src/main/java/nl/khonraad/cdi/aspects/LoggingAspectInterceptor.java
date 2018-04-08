package nl.khonraad.cdi.aspects;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor @LoggingAspect @Priority( Interceptor.Priority.APPLICATION ) public class LoggingAspectInterceptor {

    @AroundInvoke
    public Object logMethodEntry( InvocationContext ctx ) throws Exception {

        return ctx.proceed();
    }
}