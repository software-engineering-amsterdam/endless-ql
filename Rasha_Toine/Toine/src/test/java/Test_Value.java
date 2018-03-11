
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.booleans;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;
import static org.quicktheories.generators.SourceDSL.bigDecimals;

import java.math.BigDecimal;

import org.junit.Test;

import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;

public class Test_Value {

	Value booleanValue( boolean i ) {
		return new Value( Type.Boolean, i ? "True" : "False" );
	}

	Value integerValue( int i ) {
		return new Value( Type.Integer, Integer.toString( i ) );
	}

	Value stringValue( String s ) {
		return new Value( Type.String, s );
	}

	Value moneyValue( BigDecimal s ) {
		return new Value( Type.Money, s.toString() );
	}

	@Test
	public void boolean_and_boolean() {
		qt().forAll( booleans().all(), booleans().all() ).check(
				( φ, ψ ) -> booleanValue( φ ).apply( "&&", booleanValue( ψ ) ).equals( booleanValue( φ && ψ ) ) );
	}

	@Test
	public void boolean_or_boolean() {
		qt().forAll( booleans().all(), booleans().all() ).check(
				( φ, ψ ) -> booleanValue( φ ).apply( "||", booleanValue( ψ ) ).equals( booleanValue( φ || ψ ) ) );
	}

	@Test
	public void string_plus_string() {
		qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ),
				strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ) )
				.check( ( s1, s2 ) -> stringValue( s1 ).apply( "+", stringValue( s2 ) )
						.equals( stringValue( s1 + s2 ) ) );
	}

	@Test
	public void string_plus_integer() {
		qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ), integers().all() )
				.check( ( string, integer ) -> stringValue( string ).apply( "+", integerValue( integer ) )
						.equals( stringValue( string + integer ) ) );
	}

	@Test
	public void string_plus_money() {
		qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 10000 ),
				bigDecimals().ofBytes( 32 ).withScale( 2 ) )
				.check( ( string, money ) -> stringValue( string ).apply( "+", moneyValue( money ) )
						.equals( stringValue( string + money.toString() ) ) );
	}

	@Test
	public void integer_plus_integer() {
		qt().forAll( integers().between( -(Integer.MAX_VALUE) / 2, (Integer.MAX_VALUE) / 2 ),
				integers().between( -(Integer.MAX_VALUE) / 2, (Integer.MAX_VALUE) / 2 ) )
				.check( ( integer1, integer2 ) -> integerValue( integer1 ).apply( "+", integerValue( integer2 ) )
						.equals( integerValue( integer1 + integer2 ) ) );
	}

}