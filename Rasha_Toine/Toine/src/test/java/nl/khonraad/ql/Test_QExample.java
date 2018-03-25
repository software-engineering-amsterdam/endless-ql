package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.dynamics.Questionnaire;

public class Test_QExample {

    @Test
    public void test_Calculations() throws Exception {

        InputStream stream = getClass().getResourceAsStream( "/Example" );

        Questionnaire questionnaire = new Questionnaire( stream );

        questionnaire.visit();

        questionnaire.findAnswerable( "hasSoldHouse" ).setValue( Value.TRUE );
        questionnaire.findAnswerable( "hasBoughtHouse" ).setValue( Value.TRUE );
        questionnaire.findAnswerable( "hasMaintLoan" ).setValue( Value.TRUE );

        assertNull( questionnaire.findAnswerable( "sellingPrice" ) );
        assertNull( questionnaire.findAnswerable( "privateDebt" ) );

        questionnaire.visit();

        assertNotNull( questionnaire.findAnswerable( "sellingPrice" ) );
        assertNotNull( questionnaire.findAnswerable( "privateDebt" ) );

        questionnaire.findAnswerable( "sellingPrice" ).setValue( new Value( Type.Money, "1000000.00" ) );
        questionnaire.findAnswerable( "privateDebt" ).setValue( new Value( Type.Money, "800000.00" ) );

        questionnaire.visit();

        assertEquals( "a", "200000.00", questionnaire.findComputed( "valueResidue" ).getValue().getText() );

    }
}
