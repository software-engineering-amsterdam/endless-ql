package nl.khonraad.qls;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.ast.ExtendedQLSBaseVisitor;
import nl.khonraad.ql.ast.data.QLSAstBuilder;
import nl.khonraad.ql.cdisupport.LoggerProducer;
import nl.khonraad.ql.cdisupport.QLSource;

public class Test_CollegeExample {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            QLSource.class, 
            QLSAstBuilder.class,
            ExtendedQLSBaseVisitor.class,
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( QLSource.class ).get().setPath( "/nl/khonraad/qls/integration/CollegeExample.qls" );
        ExtendedQLSBaseVisitor visitor = weld.select( ExtendedQLSBaseVisitor.class ).get();
        QLSAstBuilder ast = weld.select( QLSAstBuilder.class ).get();
        visitor.visit(  ast.ast );

    }
}