package nl.khonraad.ql.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.data.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Repository;
import nl.khonraad.ql.cdi.LoggerProducer;
import nl.khonraad.ql.cdi.SourcePathProvider;

public class Test_CollegeExample {

    @Rule
    public WeldInitiator weld = WeldInitiator.from( 
            SourcePathProvider.class, 
            QLAbstractSyntaxTreeBuilder.class, 
            ExtendedQLBaseVisitor.class, 
            Questionnaire.class, 
            Repository.class, 
            LoggerProducer.class
    ).activate( ApplicationScoped.class ).build();

    @Test
    public void test_Calculations() throws Exception {

        weld.select( SourcePathProvider.class ).get().setSourcePath( "/nl/khonraad/ql/integration/CollegeExample.ql" );
        Questionnaire questionnaire = weld.select( Questionnaire.class ).get();
        ExtendedQLBaseVisitor visitor = weld.select( ExtendedQLBaseVisitor.class ).get();

        questionnaire.prepareAndVisit( visitor );

        questionnaire.storeAnswer( new Identifier( "hasSoldHouse" ), new Value( true ) );
        questionnaire.storeAnswer( new Identifier( "hasBoughtHouse" ), new Value( true ) );
        questionnaire.storeAnswer( new Identifier( "hasMaintLoan" ), new Value( true ) );

        assertNull( questionnaire.findAnswerable( new Identifier( "sellingPrice" ) ) );
        assertNull( questionnaire.findAnswerable( new Identifier( "privateDebt" ) ) );

        questionnaire.prepareAndVisit( visitor );

        assertNotNull( questionnaire.findAnswerable( new Identifier( "sellingPrice" ) ) );
        assertNotNull( questionnaire.findAnswerable( new Identifier( "privateDebt" ) ) );

        questionnaire.storeAnswer( new Identifier( "sellingPrice" ), new Value( Type.Money, "1000000.00" ) );
        questionnaire.storeAnswer( new Identifier( "privateDebt" ), new Value( Type.Money, "800000.00" ) );

        questionnaire.prepareAndVisit( visitor );

        assertEquals( "a", "200000.00", questionnaire.findComputed( new Identifier( "valueResidue" ) ).value().string() );

    }
}
