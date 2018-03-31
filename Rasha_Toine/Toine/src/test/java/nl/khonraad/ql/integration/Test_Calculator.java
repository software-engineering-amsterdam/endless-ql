package nl.khonraad.ql.integration;

import static org.junit.Assert.assertEquals;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.data.QLAstBuilder;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Repository;
import nl.khonraad.ql.cdisupport.LoggerProducer;
import nl.khonraad.ql.cdisupport.QLSource;

public class Test_Calculator {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            QLSource.class, 
            QLAstBuilder.class, 
            ExtendedQLBaseVisitor.class, 
            Questionnaire.class, 
            Repository.class, 
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( QLSource.class ).get().setPath( "/nl/khonraad/ql/integration/Calculator.ql" );
        Questionnaire questionnaire = weld.select( Questionnaire.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        questionnaire.prepareAndVisit( visitor );

        assertEquals( "a", 14, Integer.parseInt( questionnaire.findComputed( new Identifier( "a" ) ).value().string() ) );
        assertEquals( "b", 20, Integer.parseInt( questionnaire.findComputed( new Identifier( "b" ) ).value().string() ) );
        assertEquals( "c", true, Boolean.parseBoolean( questionnaire.findComputed( new Identifier( "c" ) ).value().string() ) );
    }
}