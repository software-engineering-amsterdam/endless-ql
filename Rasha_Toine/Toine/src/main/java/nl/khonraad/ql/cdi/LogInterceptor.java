package nl.khonraad.ql.cdi;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Interceptor @Log @Priority( Interceptor.Priority.APPLICATION ) public class LogInterceptor {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object logMethodEntry( InvocationContext ctx ) throws Exception {

        logger.info( "Entering method: {}", ctx.getMethod().getName() );

        Object object = ctx.proceed();

        logger.info( "Leaving method: {}", ctx.getMethod().getName() );

        return object;
    }
}