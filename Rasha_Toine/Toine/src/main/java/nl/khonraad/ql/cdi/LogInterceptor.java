package nl.khonraad.ql.cdi;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor @Log @Priority( Interceptor.Priority.APPLICATION ) 
public class LogInterceptor {

    @AroundInvoke
    public Object logMethodEntry( InvocationContext ctx ) throws Exception {

        System.out.println( "Entering method: " + ctx.getMethod().getName() );

        Object object =ctx.proceed();
        
        System.out.println( "Leaving method: " + ctx.getMethod().getName() );

        return object;
    }
}