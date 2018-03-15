package nl.khonraad.QL;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.booleans;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;
import static org.quicktheories.generators.SourceDSL.bigDecimals;

import java.math.BigDecimal;

import org.junit.Test;

import nl.khonraad.QL.domain.Type;
import nl.khonraad.QL.domain.Value;

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
    public void not_boolean() {
        qt().forAll( booleans().all() ).check( ( φ ) -> booleanValue( φ ).apply( "!" ).equals( booleanValue( !φ ) ) );
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
    public void boolean_compared_with_boolean() {
        qt().forAll( booleans().all(), booleans().all() )
                .check( ( left_boolean, right_boolean ) -> booleanValue( left_boolean )
                        .apply( "==", booleanValue( right_boolean ) )
                        .equals( booleanValue( left_boolean.equals( right_boolean ) ) ) );
    }

    @Test
    public void string_plus_string() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ),
                strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ) )
                .check( ( left_string, right_string ) -> stringValue( left_string )
                        .apply( "+", stringValue( right_string ) )
                        .equals( stringValue( left_string + right_string ) ) );
    }

    @Test
    public void string_plus_integer() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ), integers().all() )
                .check( ( string, integer ) -> stringValue( string ).apply( "+", integerValue( integer ) )
                        .equals( stringValue( string + integer ) ) );
    }

    @Test
    public void string_plus_money() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ),
                bigDecimals().ofBytes( 32 ).withScale( 2 ) )
                .check( ( string, money ) -> stringValue( string ).apply( "+", moneyValue( money ) )
                        .equals( stringValue( string + money.toString() ) ) );
    }

    @Test
    public void min_integer() {
        qt().forAll( integers().all() ).check(
                ( left_integer ) -> integerValue( left_integer ).apply( "-" ).equals( integerValue( -left_integer ) ) );
    }

    @Test
    public void integer_times_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "*", integerValue( right_integer ) )
                        .equals( integerValue( left_integer * right_integer ) ) );
    }

    @Test
    public void integer_dividedBy_integer() {
        qt().forAll( integers().all(), integers().all().assuming( integer -> integer != 0 ) )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "/", integerValue( right_integer ) )
                        .equals( integerValue( left_integer / right_integer ) ) );
    }

    @Test
    public void integer_plus_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "+", integerValue( right_integer ) )
                        .equals( integerValue( left_integer + right_integer ) ) );
    }

    @Test
    public void integer_min_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "-", integerValue( right_integer ) )
                        .equals( integerValue( left_integer - right_integer ) ) );
    }

    @Test
    public void integer_compared_with_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "==", integerValue( right_integer ) )
                        .equals( booleanValue( left_integer.equals( right_integer ) ) ) );
    }

    @Test
    public void integer_isLowerThen_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "<", integerValue( right_integer ) )
                        .equals( booleanValue( left_integer < right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThen_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( ">", integerValue( right_integer ) )
                        .equals( booleanValue( left_integer > right_integer ) ) );
    }

    @Test
    public void integer_isLowerThenOrEqualTo_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( "<=", integerValue( right_integer ) )
                        .equals( booleanValue( left_integer <= right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThenOrEqualTo_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> integerValue( left_integer )
                        .apply( ">=", integerValue( right_integer ) )
                        .equals( booleanValue( left_integer >= right_integer ) ) );
    }
}