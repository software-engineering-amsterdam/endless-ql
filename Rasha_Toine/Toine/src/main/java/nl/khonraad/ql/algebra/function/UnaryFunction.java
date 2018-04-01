package nl.khonraad.ql.algebra.function;

import nl.khonraad.ql.algebra.value.Operator;
import nl.khonraad.ql.algebra.value.Type;

public enum UnaryFunction {

    NotBoolean(Operator.Not, Type.Boolean),

    PlusInteger(Operator.Plus, Type.Integer),

    PlusMoney(Operator.Plus, Type.Money),

    MinusMoney(Operator.Minus, Type.Money),

    MinusInteger(Operator.Minus, Type.Integer);

    private Operator operator;
    private Type     right;

    private UnaryFunction( Operator operator, Type right ) {
        
        this.operator = operator;
        this.right = right;
    }

    public static UnaryFunction signature( Operator operator, Type right ) {

        for ( UnaryFunction expression : values() ) {

            if ( expression.operator.equals( operator ) && expression.right.equals( right ) ) {
                return expression;
            }
        }
        throw new RuntimeException( "Check your grammar. Do not know how to instantiate an Expression from this." );
    }
}
