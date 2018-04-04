package nl.khonraad.ql.integration;

import static org.junit.Assert.assertEquals;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Survey;
import nl.khonraad.ql.ast.data.Repository;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.qls.ast.data.StyleElements;

public class Test_Calculator {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            StyleElements.class,
            QLAbstractSyntaxTreeBuilder.class, 
            ExtendedQLBaseVisitor.class, 
            Survey.class, 
            Repository.class, 
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/Calculator.ql" );
        Questionnaire questionnaire = weld.select( Survey.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        questionnaire.visitSource( visitor );

        assertEquals( "a", 14, Integer.parseInt( questionnaire.findComputedQuestion( new Identifier( "a" ) ).get().value().string() ) );
        assertEquals( "b", 20, Integer.parseInt( questionnaire.findComputedQuestion( new Identifier( "b" ) ).get().value().string() ) );
        assertEquals( "c", true, Boolean.parseBoolean( questionnaire.findComputedQuestion( new Identifier( "c" ) ).get().value().string() ) );
    }
}