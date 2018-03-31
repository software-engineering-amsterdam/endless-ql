package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.ast.Visitor;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Repository;
import nl.khonraad.ql.cdiSupport.LoggerProducer;
import nl.khonraad.ql.cdiSupport.QLSource;

public class Test_QLCalculator {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            QLSource.class, 
            Visitor.class, 
            Questionnaire.class, 
            Repository.class, 
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( QLSource.class ).get().setPath( "/Calculator" );
        Questionnaire questionnaire = weld.select( Questionnaire.class ).get();
        Visitor visitor = weld.select( Visitor.class ).get();

        questionnaire.prepareAndVisit( visitor );

        assertEquals( "a", 14, Integer.parseInt( questionnaire.findComputed( new Identifier( "a" ) ).value().string() ) );
        assertEquals( "b", 20, Integer.parseInt( questionnaire.findComputed( new Identifier( "b" ) ).value().string() ) );
        assertEquals( "c", true, Boolean.parseBoolean( questionnaire.findComputed( new Identifier( "c" ) ).value().string() ) );
    }
}