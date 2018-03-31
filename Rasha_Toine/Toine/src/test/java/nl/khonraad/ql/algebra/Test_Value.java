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

import nl.khonraad.ql.algebra.value.Operator;

public class Test_Value {

    static int MAX_STRINGLENGTH_TO_TEST = 512;

    Value guarded_apply( Value l, Operator operator, Value r ) {

        try {
            return l.apply( operator, r );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    Value guarded_apply( Value l, Operator operator ) {

        try {
            return l.apply( operator );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    @Test
    public void not_boolean() {
        qt().forAll( booleans().all() ).check( (
                φ ) -> guarded_apply( new Value( φ ), Operator.Not ).equals( new Value( !φ ) ) );
    }

    @Test
    public void boolean_and_boolean() {

        qt().forAll( booleans().all(), booleans().all() ).check( ( φ,
                ψ ) -> guarded_apply( new Value( φ ), Operator.And, new Value( ψ ) ).equals( new Value( φ && ψ ) ) );
    }

    @Test
    public void boolean_or_boolean() {
        qt().forAll( booleans().all(), booleans().all() ).check( ( φ,
                ψ ) -> guarded_apply( new Value( φ ), Operator.Or, new Value( ψ ) ).equals( new Value( φ || ψ ) ) );
    }

    @Test
    public void boolean_compared_with_boolean() {
        qt().forAll( booleans().all(), booleans().all() ).check( ( φ,
                ψ ) -> guarded_apply( new Value( φ ), Operator.Equals, new Value( ψ ) ).equals( new Value( φ.equals( ψ ) ) ) );
    }

    @Test
    public void string_plus_string() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ) ).check( (
                left_string,
                right_string ) -> guarded_apply( new Value( left_string ), Operator.Plus, new Value( right_string ) ).equals( new Value( left_string
                        + right_string ) ) );
    }

    @Test
    public void string_plus_integer() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), integers().all() ).check( (
                string,
                integer ) -> guarded_apply( new Value( string ), Operator.Plus, new Value( integer ) ).equals( new Value( string
                        + integer ) ) );
    }

    @Test
    public void string_plus_money() {
        qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), randomBigDecimals() ).check( (
                string,
                money ) -> guarded_apply( new Value( string ), Operator.Plus, new Value( money ) ).equals( new Value( string
                        + money.toString() ) ) );
    }

    @Test
    public void min_integer() {
        qt().forAll( integers().all() ).check( (
                left_integer ) -> guarded_apply( new Value( left_integer ), Operator.Minus ).equals( new Value( -left_integer ) ) );
    }

    @Test
    public void integer_times_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.Multiply, new Value( right_integer ) ).equals( new Value( left_integer
                        * right_integer ) ) );
    }

    @Test
    public void integer_dividedBy_integer() {
        qt().forAll( integers().all(), integers().all().assuming( integer -> integer != 0 ) ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.DividedBy, new Value( right_integer ) ).equals( new Value( left_integer
                        / right_integer ) ) );
    }

    @Test
    public void integer_plus_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.Plus, new Value( right_integer ) ).equals( new Value( left_integer
                        + right_integer ) ) );
    }

    @Test
    public void integer_min_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.Minus, new Value( right_integer ) ).equals( new Value( left_integer
                        - right_integer ) ) );
    }

    @Test
    public void integer_compared_with_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.Equals, new Value( right_integer ) ).equals( new Value( left_integer.equals( right_integer ) ) ) );
    }

    @Test
    public void integer_isLowerThen_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.Less, new Value( right_integer ) ).equals( new Value( left_integer < right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThen_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.More, new Value( right_integer ) ).equals( new Value( left_integer > right_integer ) ) );
    }

    @Test
    public void integer_isLowerThenOrEqualTo_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.NotMore, new Value( right_integer ) ).equals( new Value( left_integer <= right_integer ) ) );
    }

    @Test
    public void integer_isGreaterThenOrEqualTo_integer() {
        qt().forAll( integers().all(), integers().all() ).check( ( left_integer,
                right_integer ) -> guarded_apply( new Value( left_integer ), Operator.NotLess, new Value( right_integer ) ).equals( new Value( left_integer >= right_integer ) ) );
    }

    @Test
    public void money_times_integer() {
        qt().forAll( randomBigDecimals(), integers().all() ).check( ( left_money,
                right_integer ) -> guarded_apply( new Value( left_money ), Operator.Multiply, new Value( right_integer ) ).equals( new Value( left_money.multiply( new BigDecimal( right_integer ) ) ) ) );
    }

    @Test
    public void money_plus_money() {
        qt().forAll( randomBigDecimals(), randomBigDecimals() ).check( ( left,
                right ) -> guarded_apply( new Value( left ), Operator.Plus, new Value( right ) ).equals( new Value( left.add( right ) ) ) );
    }

    @Test
    public void money_minus_money() {
        qt().forAll( randomBigDecimals(), randomBigDecimals() ).check( ( left,
                right ) -> guarded_apply( new Value( left ), Operator.Minus, new Value( right ) ).equals( new Value( left.subtract( right ) ) ) );
    }
    
    final long MILLSECONDS_EPOCH_PLUS_100YEARS = 3131202899000L;

    @Test
    public void date_plus_integer() {
        qt().forAll( randomDate(), integers().from( 0 ).upTo( 365 * 1000 ) ).check( ( date,
                right_integer ) -> guarded_apply( new Value( new DateTime( date ) ), Operator.Plus, new Value( right_integer ) ).equals( new Value( new DateTime( date ).plusDays( right_integer ) ) ) );
    }

    @Test
    public void date_min_integer() {
        qt().forAll( randomDate(), integers().from( 0 ).upTo( 365 * 1000 ) ).check( ( date,
                right_integer ) -> guarded_apply( new Value( new DateTime( date ) ), Operator.Minus, new Value( right_integer ) ).equals( new Value( new DateTime( date ).minusDays( right_integer ) ) ) );
    }

    private Gen<BigDecimal> randomBigDecimals() {
        return bigDecimals().ofBytes( 32 ).withScale( 2 );
    }

    private Gen<Date> randomDate() {
        return dates().withMillisecondsBetween( 0L, MILLSECONDS_EPOCH_PLUS_100YEARS );
    }

}