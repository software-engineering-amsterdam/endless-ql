package nl.khonraad.ql.algebra;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.bigDecimals;
import static org.quicktheories.generators.SourceDSL.booleans;
import static org.quicktheories.generators.SourceDSL.dates;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.quicktheories.core.Gen;

import nl.khonraad.ql.algebra.values.Operator;
import nl.khonraad.ql.algebra.values.Value;

public class Test_Value {

    static int MAX_STRINGLENGTH_TO_TEST = 512;

    Value applying( Operator operator, Value l, Value r ) {
        return l.apply( operator, r );
    }

    Value applying( Operator operator, Value l ) {
        return l.apply( operator );
    }
    
    @Test
    public void not_boolean() {
        qt()
        .forAll( randomBooleans() )
        .check( ( φ ) -> applying( Operator.Not, Value.of( φ ) ).equals( Value.of( !φ ) ) );
    }

    @Test
    public void boolean_and_boolean() {
        qt()
        .forAll( randomBooleans(), randomBooleans() )
        .check( ( φ, ψ ) -> applying( Operator.And, Value.of( φ ), Value.of( ψ ) ).equals( Value.of( φ && ψ ) ) );
    }

    @Test
    public void boolean_or_boolean() {
        qt()
        .forAll( randomBooleans(), randomBooleans() )
        .check( ( φ, ψ ) -> applying( Operator.Or, Value.of( φ ), Value.of( ψ ) ).equals( Value.of( φ || ψ ) ));
    }

    @Test
    public void boolean_compared_with_boolean() {
        qt()
        .forAll( randomBooleans(), randomBooleans() )
        .check( ( φ, ψ ) -> applying( Operator.Equals, Value.of( φ ), Value.of( ψ ) ).equals( Value.of( φ.equals( ψ ) ) ) );
    }

    @Test
    public void string_plus_string() {
        qt()
        .forAll( randomStrings(), randomStrings() )
        .check( ( left_string, right_string ) -> applying( Operator.Plus, Value.of( left_string ), Value.of( right_string ) ).equals( Value.of( left_string + right_string ) ) );
    }

    @Test
    public void string_plus_integer() {
        qt()
        .forAll( randomStrings(), randomIntegers() )
        .check( ( string, integer ) -> applying( Operator.Plus, Value.of( string ), Value.of( integer ) ).equals( Value.of( string + integer ) ) );
    }

    @Test
    public void string_plus_money() {
        qt()
        .forAll( randomStrings(), randomMoneys() )
        .check( ( string, money ) -> applying( Operator.Plus, Value.of( string ), Value.of( money ) ).equals( Value.of( string + money.toString() ) ) );
    }

    @Test
    public void min_integer() {
        qt()
        .forAll( randomIntegers() )
        .check( ( integer ) -> applying( Operator.Minus, Value.of( integer ) ).equals( Value.of( -integer ) ) );
    }

    @Test
    public void integer_times_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.Multiply, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer * right_integer ) ) );
    }

    @Test
    public void integer_dividedBy_integer() {
        qt()
        .forAll( randomIntegers(), randomNonZeroIntegerValues() )
        .check( ( left_integer, right_integer ) -> applying( Operator.DividedBy, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer / right_integer ) ) );
    }

    @Test
    public void integer_plus_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.Plus, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer + right_integer ) ) );
    }

    @Test
    public void integer_min_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.Minus, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer - right_integer ) ) );
    }

    @Test
    public void integer_compared_with_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.Equals, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer.equals( right_integer ) ) ) );
    }

    @Test
    public void integer_isLowerThen_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.Less, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer < right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThen_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.More, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer > right_integer ) ) );
    }

    @Test
    public void integer_isLowerThenOrEqualTo_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.NotMore, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer <= right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThenOrEqualTo_integer() {
        qt()
        .forAll( randomIntegers(), randomIntegers() )
        .check( ( left_integer, right_integer ) -> applying( Operator.NotLess, Value.of( left_integer ), Value.of( right_integer ) ).equals( Value.of( left_integer >= right_integer ) ) );
    }

    @Test
    public void money_times_integer() {
        qt()
        .forAll( randomMoneys(), randomIntegers() )
        .check( ( left_money, right_integer ) -> applying( Operator.Multiply, Value.of( left_money ), Value.of( right_integer ) ).equals( Value.of( left_money.multiply( new BigDecimal( right_integer ) ) ) ) );
    }

    @Test
    public void money_plus_money() {
        qt()
        .forAll( randomMoneys(), randomMoneys() )
        .check( ( left, right ) -> applying( Operator.Plus, Value.of( left ), Value.of( right ) ).equals( Value.of( left.add( right ) ) ) );
    }

    @Test
    public void money_minus_money() {
        qt()
        .forAll( randomMoneys(), randomMoneys() )
        .check( ( left, right ) -> applying( Operator.Minus, Value.of( left ), Value.of( right ) ).equals( Value.of( left.subtract( right ) ) ) );
    }

    @Test
    public void date_plus_integer() {
        qt()
        .forAll( randomDatesForTheComing70YearsOrSo(), randomPositiveIntegersUpTo365000() )
        .check( ( date, right_integer ) -> applying( Operator.Plus, Value.of( new DateTime( date ) ), Value.of( right_integer ) ).equals( Value.of( new DateTime( date ).plusDays( right_integer ) ) ) );
    }

    @Test
    public void date_min_integer() {
        qt()
        .forAll( randomDatesForTheComing70YearsOrSo(), randomPositiveIntegersUpTo365000() )
        .check( ( date, right_integer ) -> applying( Operator.Minus, Value.of( new DateTime( date ) ), Value.of( right_integer ) ).equals( Value.of( new DateTime( date ).minusDays( right_integer ) ) ) );
    }

    /*
     * Generators
     */
    private Gen<Boolean> randomBooleans() {
        return booleans().all();
    }

    private Gen<String> randomStrings() {
        return strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST );
    }

    private Gen<Integer> randomIntegers() {
        return integers().all();
    }

    private Gen<Integer> randomPositiveIntegersUpTo365000() {
        return integers().from( 0 ).upTo( 365 * 1000 );
    }

    private Gen<BigDecimal> randomMoneys() {
        return bigDecimals().ofBytes( 32 ).withScale( 2 );
    }

    private Gen<Integer> randomNonZeroIntegerValues() {
        return randomIntegers().assuming( integer -> integer != 0 );
    }

    private Gen<Date> randomDatesForTheComing70YearsOrSo() {
        final long MILLSECONDS_EPOCH_PLUS_100YEARS = 3131202899000L;
        return dates().withMillisecondsBetween( 0L, MILLSECONDS_EPOCH_PLUS_100YEARS );
    }
}