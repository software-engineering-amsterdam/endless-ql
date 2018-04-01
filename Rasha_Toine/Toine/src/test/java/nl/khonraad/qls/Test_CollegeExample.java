package nl.khonraad.qls;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.ast.ExtendedQLSBaseVisitor;
import nl.khonraad.ql.ast.QLSAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;

public class Test_CollegeExample {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            QLSAbstractSyntaxTreeBuilder.class,
            ExtendedQLSBaseVisitor.class,
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePath( "/nl/khonraad/qls/integration/CollegeExample.qls" );
        ExtendedQLSBaseVisitor visitor = weld.select( ExtendedQLSBaseVisitor.class ).get();
        QLSAbstractSyntaxTreeBuilder builder = weld.select( QLSAbstractSyntaxTreeBuilder.class ).get();
        visitor.visit(  builder.getTree() );

    }
}