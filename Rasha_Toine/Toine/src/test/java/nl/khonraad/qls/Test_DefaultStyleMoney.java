package nl.khonraad.qls;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.qls.ast.ExtendedQLSBaseVisitor;
import nl.khonraad.qls.ast.QLSAbstractSyntaxTreeBuilder;
import nl.khonraad.qls.language.QLSLanguage;

public class Test_DefaultStyleMoney {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            QLSAbstractSyntaxTreeBuilder.class,
            ExtendedQLSBaseVisitor.class,
            QLSLanguage.class,
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQLS( "/nl/khonraad/qls/integration/DefaultStyleMoney.qls" );
        ExtendedQLSBaseVisitor visitor = weld.select( ExtendedQLSBaseVisitor.class ).get();
        QLSAbstractSyntaxTreeBuilder builder = weld.select( QLSAbstractSyntaxTreeBuilder.class ).get();
        visitor.visit(  builder.getTree() );

    }
}