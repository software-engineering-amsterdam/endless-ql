//package nl.khonraad.QL;
//
//import static org.quicktheories.QuickTheory.qt;
//import static org.quicktheories.generators.SourceDSL.booleans;
//import static org.quicktheories.generators.SourceDSL.integers;
//import static org.quicktheories.generators.SourceDSL.strings;
//import static org.quicktheories.generators.SourceDSL.bigDecimals;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import org.antlr.v4.runtime.RecognitionException;
//import org.junit.Test;
//
//import nl.khonraad.ql.domain.Value;
//import nl.khonraad.ql.domain.Questionnaire;
//import nl.khonraad.ql.domain.Type;
//
//public class Test_Form {
//
//    Value booleanValue( boolean b ) {
//        return b ? Value.TRUE : Value.FALSE;
//    }
//
//    Value integerValue( int i ) {
//        return new Value( Type.Integer, Integer.toString( i ) );
//    }
//
//    Value stringValue( String s ) {
//        return new Value( Type.String, s );
//    }
//
//    Value moneyValue( BigDecimal m ) {
//        return new Value( Type.Money, m.toString() );
//    }
//
//    @Test
//    public void test_label() {
//        qt().forAll( integers().all()).check( i -> {
//            parse( "form " + UUID.( i ).toString() + " {}" );
//            return true;
//        } );
//    }
//
//    void parse( String s ) {
//        Questionnaire questionnaire;
//        try {
//            questionnaire = new Questionnaire( s );
//            questionnaire.visit();
//        } catch (RecognitionException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//
//}