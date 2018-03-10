
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.antlr.v4.runtime.tree.ParseTree;
import org.javatuples.Pair;
import org.junit.Test;

import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;
import nl.khonraad.visitors.InterpretingVisitor;
import utils.AbstractParserFactory;

public class TestValue {

	@Test
	public void test_Operators() throws Exception {

		Value t = new Value( Type.Boolean, "True" );
		Value f = new Value( Type.Boolean, "False" );

		assertEquals( t.apply( "!" ), f) ;
		assertEquals( f.apply( "!" ), t) ;

		assertEquals( t.apply( "&&", t ), t) ;
		assertEquals( t.apply( "&&", f ), f) ;
		assertEquals( f.apply( "&&", t ), f) ;
		assertEquals( f.apply( "&&", f ), f) ;
		
		assertEquals( t.apply( "||", t ), t) ;
		assertEquals( t.apply( "||", f ), t) ;
		assertEquals( f.apply( "||", t ), t) ;
		assertEquals( f.apply( "||", f ), f) ;
		
	
	}
}