package nl.khonraad.ql;

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

import nl.khonraad.ql.domain.Value;

public class Test_Value {

  static int MAX_STRINGLENGTH_TO_TEST = 512;

  @Test
  public void not_boolean() {
    qt().forAll( booleans().all() ).check( ( φ ) -> Value.asValue( φ ).apply( "!" ).equals( Value.asValue( !φ ) ) );
  }

  @Test
  public void boolean_and_boolean() {
    qt().forAll( booleans().all(), booleans().all() )
        .check( ( φ, ψ ) -> Value.asValue( φ ).apply( "&&", Value.asValue( ψ ) ).equals( Value.asValue( φ && ψ ) ) );
  }

  @Test
  public void boolean_or_boolean() {
    qt().forAll( booleans().all(), booleans().all() )
        .check( ( φ, ψ ) -> Value.asValue( φ ).apply( "||", Value.asValue( ψ ) ).equals( Value.asValue( φ || ψ ) ) );
  }

  @Test
  public void boolean_compared_with_boolean() {
    qt().forAll( booleans().all(), booleans().all() ).check(
        ( φ, ψ ) -> Value.asValue( φ ).apply( "==", Value.asValue( ψ ) ).equals( Value.asValue( φ.equals( ψ ) ) ) );
  }

  @Test
  public void string_plus_string() {
    qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ),
        strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ) )
        .check( ( left_string, right_string ) -> Value.asValue( left_string )
            .apply( "+", Value.asValue( right_string ) )
            .equals( Value.asValue( left_string + right_string ) ) );
  }

  @Test
  public void string_plus_integer() {
    qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), integers().all() )
        .check( ( string, integer ) -> Value.asValue( string ).apply( "+", Value.asValue( integer ) )
            .equals( Value.asValue( string + integer ) ) );
  }

  @Test
  public void string_plus_money() {
    qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), randomBigDecimals() )
        .check( ( string, money ) -> Value.asValue( string ).apply( "+", Value.asValue( money ) )
            .equals( Value.asValue( string + money.toString() ) ) );
  }

  @Test
  public void min_integer() {
    qt().forAll( integers().all() ).check( ( left_integer ) -> Value.asValue( left_integer ).apply( "-" )
        .equals( Value.asValue( -left_integer ) ) );
  }

  @Test
  public void integer_times_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "*", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer * right_integer ) ) );
  }

  @Test
  public void integer_dividedBy_integer() {
    qt().forAll( integers().all(), integers().all().assuming( integer -> integer != 0 ) )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "/", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer / right_integer ) ) );
  }

  @Test
  public void integer_plus_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "+", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer + right_integer ) ) );
  }

  @Test
  public void integer_min_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "-", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer - right_integer ) ) );
  }

  @Test
  public void integer_compared_with_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "==", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer.equals( right_integer ) ) ) );
  }

  @Test
  public void integer_isLowerThen_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "<", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer < right_integer ) ) );
  }

  @Test
  public void integer_isGreaterThen_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( ">", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer > right_integer ) ) );
  }

  @Test
  public void integer_isLowerThenOrEqualTo_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( "<=", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer <= right_integer ) ) );
  }

  @Test
  public void integer_isGreaterThenOrEqualTo_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> Value.asValue( left_integer )
            .apply( ">=", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_integer >= right_integer ) ) );
  }

  @Test
  public void money_times_integer() {
    qt().forAll( randomBigDecimals(), integers().all() )
        .check( ( left_money, right_integer ) -> Value.asValue( left_money )
            .apply( "*", Value.asValue( right_integer ) )
            .equals( Value.asValue( left_money.multiply( new BigDecimal( right_integer ) ) ) ) );
  }

  @Test
  public void money_plus_money() {
    qt().forAll( randomBigDecimals(), randomBigDecimals() ).check( ( left, right ) -> Value.asValue( left )
        .apply( "+", Value.asValue( right ) ).equals( Value.asValue( left.add( right ) ) ) );
  }

  final long MILLSECONDS_EPOCH_PLUS_100YEARS = 3131202899000L;

  @Test
  public void date_plus_integer() {
    qt().forAll( randomDate(), integers().from( 0 ).upTo( 365 * 1000 ) )
        .check( ( date, right_integer ) -> Value.asValue( new DateTime( date ) )
            .apply( "+", Value.asValue( right_integer ) )
            .equals( Value.asValue( new DateTime( date ).plusDays( right_integer ) ) ) );
  }

  @Test
  public void date_min_integer() {
    qt().forAll( randomDate(), integers().from( 0 ).upTo( 365 * 1000 ) )
        .check( ( date, right_integer ) -> Value.asValue( new DateTime( date ) )
            .apply( "-", Value.asValue( right_integer ) )
            .equals( Value.asValue( new DateTime( date ).minusDays( right_integer ) ) ) );
  }

  private Gen<BigDecimal> randomBigDecimals() {
    return bigDecimals().ofBytes( 32 ).withScale( 2 );
  }

  private Gen<Date> randomDate() {
    return dates().withMillisecondsBetween( 0L, MILLSECONDS_EPOCH_PLUS_100YEARS );
  }

}