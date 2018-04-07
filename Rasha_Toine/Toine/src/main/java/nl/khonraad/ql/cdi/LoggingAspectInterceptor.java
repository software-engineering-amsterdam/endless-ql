package nl.khonraad.ql.cdi;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor @LoggingAspect @Priority( Interceptor.Priority.APPLICATION ) public class LoggingAspectInterceptor {

    
    @AroundInvoke
    public Object logMethodEntry( InvocationContext ctx ) throws Exception {

        System.out.println( "Here we call " + ctx.getMethod().getName() + " as to" );
        Object object = ctx.proceed();

        return object;
    }
}