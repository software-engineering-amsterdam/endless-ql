package nl.khonraad.ql.domain;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class Value {

  public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormat.forPattern( "dd/MM/yyyy" );
  public static final Value             FALSE              = new Value( Type.Boolean, "False" );
  public static final Value             TRUE               = new Value( Type.Boolean, "True" );

  private Type                          type;
  private String                        text;

  public Value(Type type, String string) {

    this.type = type;
    this.text = string;
  }

  private Value(boolean condition) {

    this( Type.Boolean, condition ? TRUE.getText() : FALSE.getText() );
  }

  public Value apply( String operator ) {

    switch ( operator ) {

      case "+":
        return this;

      case "-":
        return apply( "*", new Value( Type.Integer, "-1" ) );

      case "!":
        return not( this );

      default:
        throw new RuntimeException(
            "Check Antlr grammar. Unknow unary operator defined there, not implemented here: " + operator );
    }
  }

  public Value apply( String operator, Value rightOperand ) {

    switch ( operator ) {

      case "*":
        return this.multiplied_by( rightOperand );

      case "/":
        return this.divided_by( rightOperand );

      case "+":
        return this.added_with( rightOperand );

      case "-":
        return this.subtracted_with( rightOperand );

      case "&&":
        return this.conjuncted_with( rightOperand );

      case "||":
        return this.disjuncted_with( rightOperand );

      case "==":
        return this.compared_with( rightOperand );

      case "<=":
        return this.isLowerThenOrEqualTo( rightOperand );

      case ">=":
        return this.isGreaterThenOrEqualTo( rightOperand );

      case "<":
        return this.isLowerThen( rightOperand );

      case ">":
        return this.isGreaterThen( rightOperand );

      default:
        throw new RuntimeException( "Check Antlr grammar. Operator defined there, not implemented here: " + operator );
    }
  }

  public Type getType() {
    return type;
  }

  public String getText() {
    return text;
  }

  private Value not( Value value ) {
    return new Value( !TRUE.equals( value ) );
  }

  private Value conjuncted_with( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    switch ( typeLeft + " && " + typeRight ) {

      case "Boolean && Boolean":
        return booleanAndboolean( this, rightOperand );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value disjuncted_with( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    switch ( typeLeft + " || " + typeRight ) {

      case "Boolean || Boolean":

        // use the Morgan's Theorem
        return not( booleanAndboolean( not( this ), not( rightOperand ) ) );
        
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value isLowerThen( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " < " + typeRight ) {

      case "Date < Date":
        return new Value( toDateTime( this ).isBefore( toDateTime( rightOperand ) ) );

      case "Integer < Integer":
        return new Value( Integer.parseInt( textLeft ) < Integer.parseInt( textRight ) );

      case "Money < Money":
        return new Value( new BigDecimal( textLeft ).compareTo( new BigDecimal( textRight ) ) < 0 );

      case "String < String":
        return new Value( textLeft.compareTo( textRight ) < 0 );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value isLowerThenOrEqualTo( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " <= " + typeRight ) {

      case "Date <= Date":
        return new Value(
            (toDateTime( this ).isBefore( toDateTime( rightOperand ) ) || (toDateTime( this ).equals( toDateTime( rightOperand ) ))) );

      case "Integer <= Integer":
        return new Value( (Integer.parseInt( textLeft ) <= Integer.parseInt( textRight )) );

      case "Money <= Money":
        return new Value( new BigDecimal( textLeft ).compareTo( new BigDecimal( textRight ) ) > -1 );

      case "String <= String":
        return new Value( textLeft.compareTo( textRight ) < 1 );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value compared_with( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " == " + typeRight ) {

      case "Boolean == Boolean":
      case "Date == Date":
      case "Integer == Integer":
      case "Money == Money":
      case "String == String":
        return new Value( this.equals( rightOperand ) );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible " + typeRight + " " + typeLeft + " "
            + textLeft + " " + textRight + " " );
    }
  }

  private Value isGreaterThen( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " > " + typeRight ) {

      case "Date > Date":
        return new Value( toDateTime( this ).isAfter( toDateTime( rightOperand ) ) );

      case "Integer > Integer":
        return new Value( (Integer.parseInt( textLeft ) > Integer.parseInt( textRight )) );

      case "Money > Money":
        return new Value( new BigDecimal( textLeft ).compareTo( new BigDecimal( textRight ) ) > 0 );

      case "String > String":
        return new Value( textLeft.compareTo( textRight ) > 0 );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value isGreaterThenOrEqualTo( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " >= " + typeRight ) {

      case "Date >= Date":
        return new Value(
            (toDateTime( this ).isAfter( toDateTime( rightOperand ) ) || (toDateTime( this ).equals( toDateTime( rightOperand ) ))) );

      case "Integer >= Integer":
        return new Value( (Integer.parseInt( textLeft ) >= Integer.parseInt( textRight )) );

      case "Money >= Money":
        return new Value( new BigDecimal( textLeft ).compareTo( new BigDecimal( textRight ) ) > -1 );

      case "String >= String":
        return new Value( textLeft.compareTo( textRight ) > -1 );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value multiplied_by( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();
    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " * " + typeRight ) {

      case "Integer * Integer":
        return new Value( Type.Integer,
            Integer.toString( Integer.parseInt( textLeft ) * Integer.parseInt( textRight ) ) );

      case "Integer * Money":
        return new Value( Type.Money, new BigDecimal( textRight ).multiply( new BigDecimal( textLeft ) ).toString() );

      case "Money * Integer":
        return new Value( Type.Money, new BigDecimal( textLeft ).multiply( new BigDecimal( textRight ) ).toString() );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value divided_by( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();
    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " / " + typeRight ) {

      case "Integer / Integer":
        return new Value( Type.Integer,
            Integer.toString( (Integer.parseInt( textLeft ) / Integer.parseInt( textRight )) ) );

      case "Money / Integer":
        return new Value( Type.Money, Double.toString(
            (new BigDecimal( textLeft ).divide( new BigDecimal( Integer.parseInt( textRight ) ) )).doubleValue() ) );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value added_with( Value rightOperand ) {

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( operation( "+", rightOperand ) ) {

      case "Date + Integer":
        int days = parseInteger( rightOperand );

        return new Value( Type.Date, Value.dateTimeToString( toDateTime( this ).plusDays( days ) ) );

      case "Integer + Integer":
        return new Value( Type.Integer,
            Integer.toString( (Integer.parseInt( textLeft ) + Integer.parseInt( textRight )) ) );

      case "Money + Money":
        return new Value( Type.Money, "" + new BigDecimal( textLeft ).add( new BigDecimal( textRight ) ) );

      case "String + Integer":
      case "String + Money":
      case "String + String":
        return new Value( Type.String, textLeft + textRight );

      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value subtracted_with( Value rightOperand ) {

    Type typeRight = rightOperand.getType();
    Type typeLeft = this.getType();

    String textLeft = this.getText();
    String textRight = rightOperand.getText();

    switch ( typeLeft + " - " + typeRight ) {

      case "Date - Integer":
        int days = parseInteger( rightOperand );

        return new Value( Type.Date, Value.dateTimeToString( toDateTime( this ).minusDays( days ) ) );

      case "Integer - Integer":
        return new Value( Type.Integer,
            Integer.toString( (Integer.parseInt( textLeft ) - Integer.parseInt( textRight )) ) );

      case "Money - Money":
        return new Value( Type.Money,
            Double.toString( new BigDecimal( textLeft ).subtract( new BigDecimal( textRight ) ).doubleValue() ) );
      default:
        throw new RuntimeException( "Check Antlr grammar. Operation impossible" );
    }
  }

  private Value booleanAndboolean( Value left, Value right ) {

    return new Value( TRUE.equals( left ) && TRUE.equals( right ) );
  }

  private DateTime toDateTime( Value value ) {

    return new DateTime( SIMPLE_DATE_FORMAT.parseDateTime( value.getText() ) );
  }

  private static String dateTimeToString( DateTime d ) {

    return SIMPLE_DATE_FORMAT.print( d );
  }

  @Override
  public String toString() {

    return new ToStringBuilder( this, ToStringStyle.MULTI_LINE_STYLE ).append( "type", type ).append( "text", text )
        .toString();
  }

  private static Integer parseInteger( Value value ) {
    return Integer.parseInt( valueText( value ) );
  }

  private static String valueText( Value value ) {
    return value.getText();
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((text == null) ? 0 : text.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  @Override
  public boolean equals( Object obj ) {

    if ( this == obj )
      return true;
    if ( obj == null )
      return false;
    if ( getClass() != obj.getClass() )
      return false;
    Value other = (Value) obj;
    if ( text == null ) {
      if ( other.text != null )
        return false;
    } else if ( !text.equals( other.text ) )
      return false;
    if ( type != other.type )
      return false;
    return true;
  }

  private String operation( String operator, Value rightOperand ) {
    return this.getType() + " + " + rightOperand.getType();
  }

}