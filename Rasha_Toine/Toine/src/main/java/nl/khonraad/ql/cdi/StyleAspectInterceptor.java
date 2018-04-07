package nl.khonraad.ql.cdi;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor @StyleAspect @Priority( Interceptor.Priority.APPLICATION ) public class StyleAspectInterceptor {

    
    @AroundInvoke
    public Object aroundInvoke( InvocationContext ctx ) throws Exception {

        System.out.println( "STYLEASPECT Before  + ctx" );
        
        Object object = ctx.proceed();
        
        System.out.println( "STYLEASPECT AFTER + ctx" );

        return object;
    }
    @AroundConstruct
    public Object constructor( InvocationContext ctx ) throws Exception {
        
        System.out.println( "STYLEASPECT Before  + ctx" );
        Object object = ctx.proceed();
        System.out.println( "STYLEASPECT AFTER + ctx" );
        
        return object;
    }
}