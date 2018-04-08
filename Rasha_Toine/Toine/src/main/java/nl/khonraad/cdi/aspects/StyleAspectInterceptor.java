package nl.khonraad.cdi.aspects;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor @StyleAspect @Priority( Interceptor.Priority.APPLICATION ) public class StyleAspectInterceptor {

    @AroundInvoke
    public Object aroundInvoke( InvocationContext ctx ) throws Exception {

        Object object = ctx.proceed();

        return object;
    }
}