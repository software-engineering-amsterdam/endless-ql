package nl.khonraad.ql.integration;

import static nl.khonraad.ql.algebra.values.Value.True;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.ql.domain.Questionnaire;
import nl.khonraad.ql.domain.Repository;
import nl.khonraad.ql.domain.Survey;

public class Test_CollegeExample {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( SourcePathProvider.class, QLAbstractSyntaxTreeBuilder.class, ExtendedQLBaseVisitor.class, Survey.class, Repository.class, LoggerProducer.class ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/CollegeExample.ql" );
        Questionnaire questionnaire = weld.select( Survey.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        questionnaire.visitSource( visitor );

        questionnaire.storeAnswer( questionnaire.findAnswerableQuestion( new Identifier( "hasSoldHouse" ) ).get(), True );
        questionnaire.storeAnswer( questionnaire.findAnswerableQuestion( new Identifier( "hasBoughtHouse" ) ).get(), True );
        questionnaire.storeAnswer( questionnaire.findAnswerableQuestion( new Identifier( "hasMaintLoan" ) ).get(), True );

        assertTrue( !questionnaire.findAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( !questionnaire.findAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        questionnaire.visitSource( visitor );

        assertTrue( questionnaire.findAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( questionnaire.findAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        questionnaire.storeAnswer( questionnaire.findAnswerableQuestion( new Identifier( "sellingPrice" ) ).get(), Value.typed( Type.Money, "1000000.00" ) );
        questionnaire.storeAnswer( questionnaire.findAnswerableQuestion( new Identifier( "privateDebt" ) ).get(), Value.typed( Type.Money, "800000.00" ) );

        questionnaire.visitSource( visitor );

        assertEquals( "a", "200000.00", questionnaire.findComputedQuestion( new Identifier( "valueResidue" ) ).get().value().string() );

    }
}
