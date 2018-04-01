package nl.khonraad.ql.algebra.function;

import nl.khonraad.ql.algebra.value.Operator;
import nl.khonraad.ql.algebra.value.Type;

public enum UnarySignature {

    NotBoolean(Operator.Not, Type.Boolean),

    PlusInteger(Operator.Plus, Type.Integer),

    PlusMoney(Operator.Plus, Type.Money),

    MinusMoney(Operator.Minus, Type.Money),

    MinusInteger(Operator.Minus, Type.Integer);

    private Operator operator;
    private Type     operandType;

    private UnarySignature( Operator operator, Type type ) {

        this.operator = operator;
        this.operandType = type;
    }

    public static UnarySignature signature( Operator operator, Type operandType ) {

        for ( UnarySignature candidate : values() ) {

            if ( candidate.operator.equals( operator ) && candidate.operandType.equals( operandType ) ) {
                return candidate;
            }
        }
        throw new RuntimeException( "Check your grammar. Do not know how to instantiate an Expression from this." );
    }
}
