package nl.khonraad.ql.algebra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.algebra.value.Value;

public class Test_Value_Typing {

    @Test( expected = NullPointerException.class )
    public void test_ShowIntend___UnitPrintReturnsNull() {

        Value.Unit.string().length();
    }

    @Test
    public void test_unitType() {

        assertNotNull( Value.Unit );

        assertEquals( Value.Unit, Value.Unit );
        new Value( Type.String, null ).string();
        assertEquals( new Value( Type.Date, null ).string(), null );
        assertEquals( new Value( Type.String, null ).string(), null );

    }

}
