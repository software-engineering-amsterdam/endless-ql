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

import nl.khonraad.ql.domain.Type;
import nl.khonraad.ql.domain.Value;

public class Test_Value {

  static int MAX_STRINGLENGTH_TO_TEST = 512;

  private static Value valueOfBoolean( boolean b ) {
    return b ? Value.TRUE : Value.FALSE;
  }

  private static Value valueOfInteger( int i ) {
    return new Value( Type.Integer, Integer.toString( i ) );
  }

  private static Value valueOfString( String s ) {
    return new Value( Type.String, s );
  }

  private static Value valueOfBigDecimal( BigDecimal m ) {
    return new Value( Type.Money, m.toString() );
  }

  private static Value valueOfDate( DateTime m ) {
    return new Value( Type.Date, Value.SIMPLE_DATE_FORMAT.print( m ) );
  }

  @Test
  public void not_boolean() {
    qt().forAll( booleans().all() ).check( ( φ ) -> valueOfBoolean( φ ).apply( "!" ).equals( valueOfBoolean( !φ ) ) );
  }

  @Test
  public void boolean_and_boolean() {
    qt().forAll( booleans().all(), booleans().all() )
        .check( ( φ, ψ ) -> valueOfBoolean( φ ).apply( "&&", valueOfBoolean( ψ ) ).equals( valueOfBoolean( φ && ψ ) ) );
  }

  @Test
  public void boolean_or_boolean() {
    qt().forAll( booleans().all(), booleans().all() )
        .check( ( φ, ψ ) -> valueOfBoolean( φ ).apply( "||", valueOfBoolean( ψ ) ).equals( valueOfBoolean( φ || ψ ) ) );
  }

  @Test
  public void boolean_compared_with_boolean() {
    qt().forAll( booleans().all(), booleans().all() ).check(
        ( φ, ψ ) -> valueOfBoolean( φ ).apply( "==", valueOfBoolean( ψ ) ).equals( valueOfBoolean( φ.equals( ψ ) ) ) );
  }

  @Test
  public void string_plus_string() {
    qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ),
        strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ) )
        .check( ( left_string, right_string ) -> valueOfString( left_string )
            .apply( "+", valueOfString( right_string ) ).equals( valueOfString( left_string + right_string ) ) );
  }

  @Test
  public void string_plus_integer() {
    qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), integers().all() )
        .check( ( string, integer ) -> valueOfString( string ).apply( "+", valueOfInteger( integer ) )
            .equals( valueOfString( string + integer ) ) );
  }

  @Test
  public void string_plus_money() {
    qt().forAll( strings().basicLatinAlphabet().ofLengthBetween( 0, MAX_STRINGLENGTH_TO_TEST ), randomBigDecimals() )
        .check( ( string, money ) -> valueOfString( string ).apply( "+", valueOfBigDecimal( money ) )
            .equals( valueOfString( string + money.toString() ) ) );
  }

  @Test
  public void min_integer() {
    qt().forAll( integers().all() ).check(
        ( left_integer ) -> valueOfInteger( left_integer ).apply( "-" ).equals( valueOfInteger( -left_integer ) ) );
  }

  @Test
  public void integer_times_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "*", valueOfInteger( right_integer ) ).equals( valueOfInteger( left_integer * right_integer ) ) );
  }

  @Test
  public void integer_dividedBy_integer() {
    qt().forAll( integers().all(), integers().all().assuming( integer -> integer != 0 ) )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "/", valueOfInteger( right_integer ) ).equals( valueOfInteger( left_integer / right_integer ) ) );
  }

  @Test
  public void integer_plus_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "+", valueOfInteger( right_integer ) ).equals( valueOfInteger( left_integer + right_integer ) ) );
  }

  @Test
  public void integer_min_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "-", valueOfInteger( right_integer ) ).equals( valueOfInteger( left_integer - right_integer ) ) );
  }

  @Test
  public void integer_compared_with_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "==", valueOfInteger( right_integer ) )
            .equals( valueOfBoolean( left_integer.equals( right_integer ) ) ) );
  }

  @Test
  public void integer_isLowerThen_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "<", valueOfInteger( right_integer ) ).equals( valueOfBoolean( left_integer < right_integer ) ) );
  }

  @Test
  public void integer_isGreaterThen_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( ">", valueOfInteger( right_integer ) ).equals( valueOfBoolean( left_integer > right_integer ) ) );
  }

  @Test
  public void integer_isLowerThenOrEqualTo_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( "<=", valueOfInteger( right_integer ) ).equals( valueOfBoolean( left_integer <= right_integer ) ) );
  }

  @Test
  public void integer_isGreaterThenOrEqualTo_integer() {
    qt().forAll( integers().all(), integers().all() )
        .check( ( left_integer, right_integer ) -> valueOfInteger( left_integer )
            .apply( ">=", valueOfInteger( right_integer ) ).equals( valueOfBoolean( left_integer >= right_integer ) ) );
  }

  @Test
  public void money_times_integer() {
    qt().forAll( randomBigDecimals(), integers().all() )
        .check( ( left_money, right_integer ) -> valueOfBigDecimal( left_money )
            .apply( "*", valueOfInteger( right_integer ) )
            .equals( valueOfBigDecimal( left_money.multiply( new BigDecimal( right_integer ) ) ) ) );
  }

  @Test
  public void money_plus_money() {
    qt().forAll( randomBigDecimals(), randomBigDecimals() ).check( ( left, right ) -> valueOfBigDecimal( left )
        .apply( "+", valueOfBigDecimal( right ) ).equals( valueOfBigDecimal( left.add( right ) ) ) );
  }

  final long MILLSECONDS_EPOCH_PLUS_100YEARS = 3131202899000L;

  @Test
  public void date_plus_integer() {
    qt().forAll( randomDate(), integers().from( 0 ).upTo( 365 * 1000 ) ).check(
        ( date, right_integer ) -> valueOfDate( new DateTime( date ) ).apply( "+", valueOfInteger( right_integer ) )
            .equals( valueOfDate( new DateTime( date ).plusDays( right_integer ) ) ) );
  }

  @Test
  public void date_min_integer() {
    qt().forAll( randomDate(), integers().from( 0 ).upTo( 365 * 1000 ) ).check(
        ( date, right_integer ) -> valueOfDate( new DateTime( date ) ).apply( "-", valueOfInteger( right_integer ) )
            .equals( valueOfDate( new DateTime( date ).minusDays( right_integer ) ) ) );
  }

  private Gen<BigDecimal> randomBigDecimals() {
    return bigDecimals().ofBytes( 32 ).withScale( 2 );
  }

  private Gen<Date> randomDate() {
    return dates().withMillisecondsBetween( 0L, MILLSECONDS_EPOCH_PLUS_100YEARS );
  }

}