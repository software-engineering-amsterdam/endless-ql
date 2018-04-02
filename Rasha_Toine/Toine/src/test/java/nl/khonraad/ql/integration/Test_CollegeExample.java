package nl.khonraad.ql.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.algebra.value.Value;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Survey;
import nl.khonraad.ql.ast.data.Repository;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;

public class Test_CollegeExample {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            QLAbstractSyntaxTreeBuilder.class, 
            ExtendedQLBaseVisitor.class, 
            Survey.class, 
            Repository.class, 
            LoggerProducer.class
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/CollegeExample.ql" );
        Questionnaire questionnaire = weld.select( Survey.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        questionnaire.visitSource( visitor );

        questionnaire.storeAnswer( new Identifier( "hasSoldHouse" ), new Value( true ) );
        questionnaire.storeAnswer( new Identifier( "hasBoughtHouse" ), new Value( true ) );
        questionnaire.storeAnswer( new Identifier( "hasMaintLoan" ), new Value( true ) );

        assertTrue( !questionnaire.findAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( !questionnaire.findAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        questionnaire.visitSource( visitor );

        assertNotNull( questionnaire.findAnswerableQuestion( new Identifier( "sellingPrice" ) ) );
        assertNotNull( questionnaire.findAnswerableQuestion( new Identifier( "privateDebt" ) ) );

        questionnaire.storeAnswer( new Identifier( "sellingPrice" ), new Value( Type.Money, "1000000.00" ) );
        questionnaire.storeAnswer( new Identifier( "privateDebt" ), new Value( Type.Money, "800000.00" ) );

        questionnaire.visitSource( visitor );

        assertEquals( "a", "200000.00", questionnaire.findComputedQuestion( new Identifier( "valueResidue" ) ).get().value().string() );

    }
}
