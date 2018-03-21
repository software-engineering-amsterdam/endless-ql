package nl.khonraad.ql;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.bigDecimals;
import static org.quicktheories.generators.SourceDSL.booleans;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

import java.math.BigDecimal;

import org.junit.Test;
import org.quicktheories.core.Gen;

import nl.khonraad.ql.domain.Value;

public class Test_Value {

    @Test
    public void not_boolean() {
        qt().forAll( booleans().all() )
                .check( ( φ ) -> Value.ofBoolean( φ ).apply( "!" ).equals( Value.ofBoolean( !φ ) ) );
    }

    @Test
    public void boolean_and_boolean() {
        qt().forAll( booleans().all(), booleans().all() ).check( ( φ, ψ ) -> Value.ofBoolean( φ )
                .apply( "&&", Value.ofBoolean( ψ ) ).equals( Value.ofBoolean( φ && ψ ) ) );
    }

    @Test
    public void boolean_or_boolean() {
        qt().forAll( booleans().all(), booleans().all() ).check( ( φ, ψ ) -> Value.ofBoolean( φ )
                .apply( "||", Value.ofBoolean( ψ ) ).equals( Value.ofBoolean( φ || ψ ) ) );
    }

    @Test
    public void boolean_compared_with_boolean() {
        qt().forAll( booleans().all(), booleans().all() ).check( ( φ, ψ ) -> Value.ofBoolean( φ )
                .apply( "==", Value.ofBoolean( ψ ) ).equals( Value.ofBoolean( φ.equals( ψ ) ) ) );
    }

    @Test
    public void string_plus_string() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ),
                strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ) )
                .check( ( left_string, right_string ) -> Value.ofString( left_string )
                        .apply( "+", Value.ofString( right_string ) )
                        .equals( Value.ofString( left_string + right_string ) ) );
    }

    @Test
    public void string_plus_integer() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ), integers().all() )
                .check( ( string, integer ) -> Value.ofString( string ).apply( "+", Value.ofInteger( integer ) )
                        .equals( Value.ofString( string + integer ) ) );
    }

    @Test
    public void string_plus_money() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, 20000 ), randomBigDecimals() )
                .check( ( string, money ) -> Value.ofString( string ).apply( "+", Value.ofBigDecimal( money ) )
                        .equals( Value.ofString( string + money.toString() ) ) );
    }

    @Test
    public void min_integer() {
        qt().forAll( integers().all() ).check( ( left_integer ) -> Value.ofInteger( left_integer ).apply( "-" )
                .equals( Value.ofInteger( -left_integer ) ) );
    }

    @Test
    public void integer_times_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "*", Value.ofInteger( right_integer ) )
                        .equals( Value.ofInteger( left_integer * right_integer ) ) );
    }

    @Test
    public void integer_dividedBy_integer() {
        qt().forAll( integers().all(), integers().all().assuming( integer -> integer != 0 ) )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "/", Value.ofInteger( right_integer ) )
                        .equals( Value.ofInteger( left_integer / right_integer ) ) );
    }

    @Test
    public void integer_plus_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "+", Value.ofInteger( right_integer ) )
                        .equals( Value.ofInteger( left_integer + right_integer ) ) );
    }

    @Test
    public void integer_min_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "-", Value.ofInteger( right_integer ) )
                        .equals( Value.ofInteger( left_integer - right_integer ) ) );
    }

    @Test
    public void integer_compared_with_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "==", Value.ofInteger( right_integer ) )
                        .equals( Value.ofBoolean( left_integer.equals( right_integer ) ) ) );
    }

    @Test
    public void integer_isLowerThen_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "<", Value.ofInteger( right_integer ) )
                        .equals( Value.ofBoolean( left_integer < right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThen_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( ">", Value.ofInteger( right_integer ) )
                        .equals( Value.ofBoolean( left_integer > right_integer ) ) );
    }

    @Test
    public void integer_isLowerThenOrEqualTo_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( "<=", Value.ofInteger( right_integer ) )
                        .equals( Value.ofBoolean( left_integer <= right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThenOrEqualTo_integer() {
        qt().forAll( integers().all(), integers().all() )
                .check( ( left_integer, right_integer ) -> Value.ofInteger( left_integer )
                        .apply( ">=", Value.ofInteger( right_integer ) )
                        .equals( Value.ofBoolean( left_integer >= right_integer ) ) );
    }

    @Test
    public void money_times_integer() {
        qt().forAll( randomBigDecimals(), integers().all() )
                .check( ( left_money, right_integer ) -> Value.ofBigDecimal( left_money )
                        .apply( "*", Value.ofInteger( right_integer ) )
                        .equals( Value.ofBigDecimal( left_money.multiply( new BigDecimal( right_integer ) ) ) ) );
    }

    @Test
    public void money_plus_money() {
        qt().forAll( randomBigDecimals(), randomBigDecimals() ).check( ( left, right ) -> Value.ofBigDecimal( left )
                .apply( "+", Value.ofBigDecimal( right ) ).equals( Value.ofBigDecimal( left.add( right ) ) ) );
    }

    private Gen<BigDecimal> randomBigDecimals() {
        return bigDecimals().ofBytes( 32 ).withScale( 2 );
    }

}