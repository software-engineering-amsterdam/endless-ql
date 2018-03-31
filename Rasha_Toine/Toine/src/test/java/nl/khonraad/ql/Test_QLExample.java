package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;

import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Questionnaire;

public class Test_QLExample {

    @Test
    public void test_Calculations() throws Exception {

        InputStream stream = getClass().getResourceAsStream( "/Example" );

        Questionnaire questionnaire = new Questionnaire( stream );

        questionnaire.visit();

        questionnaire.storeAnswer( new Identifier( "hasSoldHouse" ), new Value( true ) );
        questionnaire.storeAnswer( new Identifier( "hasBoughtHouse" ), new Value( true ) );
        questionnaire.storeAnswer( new Identifier( "hasMaintLoan" ), new Value( true ) );

        assertNull( questionnaire.findAnswerable( new Identifier( "sellingPrice" ) ) );
        assertNull( questionnaire.findAnswerable( new Identifier( "privateDebt" ) ) );

        questionnaire.visit();

        assertNotNull( questionnaire.findAnswerable( new Identifier( "sellingPrice" ) ) );
        assertNotNull( questionnaire.findAnswerable( new Identifier( "privateDebt" ) ) );

        questionnaire.storeAnswer( new Identifier( "sellingPrice" ), new Value( Type.Money, "1000000.00" ) );
        questionnaire.storeAnswer( new Identifier( "privateDebt" ), new Value( Type.Money, "800000.00" ) );

        questionnaire.visit();

        assertEquals( "a", "200000.00", questionnaire.findComputed( new Identifier( "valueResidue" ) ).value().string() );

    }
}
