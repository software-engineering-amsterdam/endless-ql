package nl.khonraad.ql.algebra.functions;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Operator;

public enum BinarySignature {

    BooleanAndBoolean(          Type.Boolean,   Operator.And,       Type.Boolean ),
    BooleanEqualsBoolean(       Type.Boolean,   Operator.Equals,    Type.Boolean ),
    BooleanOrBoolean(           Type.Boolean,   Operator.Or,        Type.Boolean ),
                                
    DateEqualsDate(             Type.Date,      Operator.Equals,    Type.Date    ),
    DateLessDate(               Type.Date,      Operator.Less,      Type.Date    ),
    DateMinusInteger(           Type.Date,      Operator.Minus,     Type.Integer ),
    DateMoreDate(               Type.Date,      Operator.More,      Type.Date    ),
    DateNotLessDate(            Type.Date,      Operator.NotLess,   Type.Date    ),
    DateNotMoreDate(            Type.Date,      Operator.NotMore,   Type.Date    ),
    DatePlusInteger(            Type.Date,      Operator.Plus,      Type.Integer ),
    
    IntegerDividedByInteger(    Type.Integer,   Operator.DividedBy, Type.Integer ),
    IntegerEqualsInteger(       Type.Integer,   Operator.Equals,    Type.Integer ),
    IntegerLessInteger(         Type.Integer,   Operator.Less,      Type.Integer ),
    IntegerMinusInteger(        Type.Integer,   Operator.Minus,     Type.Integer ),
    IntegerMoreInteger(         Type.Integer,   Operator.More,      Type.Integer ),
    IntegerNotLessInteger(      Type.Integer,   Operator.NotLess,   Type.Integer ),
    IntegerNotMoreInteger(      Type.Integer,   Operator.NotMore,   Type.Integer ),
    IntegerPlusInteger(         Type.Integer,   Operator.Plus,      Type.Integer ),
    IntegerTimesInteger(        Type.Integer,   Operator.Multiply,  Type.Integer ),
    IntegerTimesMoney(          Type.Integer,   Operator.Multiply,  Type.Money   ),
    
    MoneyDividedByInteger(      Type.Money,     Operator.DividedBy, Type.Integer ),
    MoneyEqualsMoney(           Type.Money,     Operator.Equals,    Type.Money   ),
    MoneyLessMoney(             Type.Money,     Operator.Less,      Type.Money   ),
    MoneyMoreMoney(             Type.Money,     Operator.More,      Type.Money   ),
    MoneyNotLessMoney(          Type.Money,     Operator.NotLess,   Type.Money   ),
    MoneyNotMoreMoney(          Type.Money,     Operator.NotMore,   Type.Money   ),
    MoneyPlusMoney(             Type.Money,     Operator.Plus,      Type.Money   ),
    MoneyTimesInteger(          Type.Money,     Operator.Multiply,  Type.Integer ),
    MoneyMinusMoney(            Type.Money,     Operator.Minus,     Type.Money   ),
    
    StringEqualsString(         Type.String,    Operator.Equals,    Type.String  ),
    StringLessString(           Type.String,    Operator.Less,      Type.String  ),
    StringMoreString(           Type.String,    Operator.More,      Type.String  ),
    StringNotLessString(        Type.String,    Operator.NotLess,   Type.String  ),
    StringNotMoreString(        Type.String,    Operator.NotMore,   Type.String  ),
    StringPlusInteger(          Type.String,    Operator.Plus,      Type.Integer ),
    StringPlusMoney(            Type.String,    Operator.Plus,      Type.Money   ),
    StringPlusString(           Type.String,    Operator.Plus,      Type.String  );

    private Type     leftType;
    private Operator operator;
    private Type     rightType;

    private BinarySignature(Type leftType, Operator operator, Type rightType) {
        this.leftType = leftType;
        this.operator = operator;
        this.rightType = rightType;
    }

    public static BinarySignature signature( Type leftType, Operator operator, Type rightType ) {

        for ( BinarySignature candidate : values() ) {

            if ( candidate.leftType.equals( leftType ) && candidate.operator.equals( operator )
                    && candidate.rightType.equals( rightType ) ) {
                return candidate;
            }
        }
        throw new RuntimeException( "Check your grammar. Do not know how to instantiate an Expression from this." );
    }
}
