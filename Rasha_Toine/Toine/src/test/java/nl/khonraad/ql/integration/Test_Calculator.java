package nl.khonraad.ql.integration;

import static org.junit.Assert.assertEquals;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.cdi.producers.LoggerProducer;
import nl.khonraad.cdi.producers.SourcePathProvider;
import nl.khonraad.ql.language.Identifier;
import nl.khonraad.ql.language.QuestionsInterpretor;
import nl.khonraad.ql.language.QuestionsLanguage;
import nl.khonraad.ql.parser.QuestionsVisitor;
import nl.khonraad.ql.parser.QuestionsAST;

public class Test_Calculator {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            QuestionsAST.class, 
            QuestionsVisitor.class, 
            QuestionsLanguage.class, 
            LoggerProducer.class 
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePathQL( "/nl/khonraad/ql/integration/Calculator.ql" );
        QuestionsInterpretor questionsInterpretor = weld.select( QuestionsLanguage.class ).get();
        QuestionsVisitor visitor = weld.select( QuestionsVisitor.class ).get();

        questionsInterpretor.visitSource( visitor );

        assertEquals( "a", 14, Integer.parseInt( questionsInterpretor.queryComputedQuestion( new Identifier( "a" ) ).get().value().string() ) );
        assertEquals( "b", 20, Integer.parseInt( questionsInterpretor.queryComputedQuestion( new Identifier( "b" ) ).get().value().string() ) );
        assertEquals( "c", true, Boolean.parseBoolean( questionsInterpretor.queryComputedQuestion( new Identifier( "c" ) ).get().value().string() ) );
    }
}