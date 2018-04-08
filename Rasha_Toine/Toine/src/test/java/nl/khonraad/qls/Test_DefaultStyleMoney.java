package nl.khonraad.qls;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.cdi.producers.LoggerProducer;
import nl.khonraad.cdi.producers.SourcePathProvider;
import nl.khonraad.qls.language.StylesLanguage;
import nl.khonraad.qls.parser.StylesVisitor;
import nl.khonraad.qls.parser.StylesAST;

public class Test_DefaultStyleMoney {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            StylesAST.class,
            StylesVisitor.class,
            StylesLanguage.class,
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQLS( "/nl/khonraad/qls/integration/DefaultStyleMoney.qls" );
        StylesVisitor visitor = weld.select( StylesVisitor.class ).get();
        StylesAST builder = weld.select( StylesAST.class ).get();
        visitor.visit(  builder.getTree() );

    }
}