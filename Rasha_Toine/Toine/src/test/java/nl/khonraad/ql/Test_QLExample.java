package nl.khonraad.ql;

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
import nl.khonraad.ql.ast.Visitor;
import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.ast.data.Repository;
import nl.khonraad.ql.cdiSupport.LoggerProducer;
import nl.khonraad.ql.cdiSupport.QLSource;

public class Test_QLExample {

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

        weld.select( QLSource.class ).get().setPath( "/Example" );
        Questionnaire questionnaire = weld.select( Questionnaire.class ).get();
        Visitor visitor = weld.select( Visitor.class ).get();

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
