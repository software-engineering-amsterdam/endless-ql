package nl.khonraad.ql.integration;

import static nl.khonraad.ql.algebra.values.Value.True;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.cdi.producers.LoggerProducer;
import nl.khonraad.cdi.producers.SourcePathProvider;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.QuestionsInterpretor;
import nl.khonraad.ql.language.QuestionsLanguage;
import nl.khonraad.ql.language.Identifier;
import nl.khonraad.ql.language.Question;
import nl.khonraad.ql.parser.QuestionsVisitor;
import nl.khonraad.ql.parser.QuestionsAST;

public class Test_CollegeExample {

    QuestionsInterpretor questionsInterpretor;

    @Rule
    public WeldInitiator weld = WeldInitiator.from( SourcePathProvider.class, QuestionsAST.class, QuestionsVisitor.class, QuestionsLanguage.class, LoggerProducer.class ).activate( ApplicationScoped.class ).build();

    private Question questionLabeled( String s ) {

        return questionsInterpretor.queryAnswerableQuestion( new Identifier( s ) ).get();
    }

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/CollegeExample.ql" );
        questionsInterpretor = weld.select( QuestionsLanguage.class ).get();
        QuestionsVisitor visitor = weld.select( QuestionsVisitor.class ).get();

        questionsInterpretor.visitSource( visitor );

        questionsInterpretor.assign( questionLabeled( "hasSoldHouse" ), True );
        questionsInterpretor.assign( questionLabeled( "hasBoughtHouse" ), True );
        questionsInterpretor.assign( questionLabeled( "hasMaintLoan" ), True );

        assertTrue( !questionsInterpretor.queryAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( !questionsInterpretor.queryAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        questionsInterpretor.visitSource( visitor );

        assertTrue( questionsInterpretor.queryAnswerableQuestion( new Identifier( "sellingPrice" ) ).isPresent() );
        assertTrue( questionsInterpretor.queryAnswerableQuestion( new Identifier( "privateDebt" ) ).isPresent() );

        questionsInterpretor.assign( questionLabeled( "sellingPrice" ), Value.typed( Type.Money, "1000000.00" ) );
        questionsInterpretor.assign( questionLabeled( "privateDebt" ), Value.typed( Type.Money, "800000.00" ) );

        questionsInterpretor.visitSource( visitor );

        assertEquals( "a", "200000.00", questionsInterpretor.queryComputedQuestion( new Identifier( "valueResidue" ) ).get().value().string() );

    }
}
