using System;

namespace Assignment1.Model.QL.AST
{
    public enum Type
    {
        Boolean,
        Integer,
        String,
        Date,
        Decimal,
        Money,
        Undefined
    }

    public static class TypeMethods
    {
        public static bool IsNumeric(this Type at) => at == Type.Integer || at == Type.Money || at == Type.Decimal;

        public static Type InferArithmeticType(Type leftType, Type rightType)
        {
            if (leftType == Type.Integer)
            {
                switch (rightType)
                {
                    case Type.Integer: return Type.Integer;
                    case Type.Money: return Type.Money;
                    case Type.Decimal: return Type.Decimal;
                }
            }
            if (leftType == Type.Decimal)
            {
                switch (rightType)
                {
                    case Type.Integer: return Type.Decimal;
                    case Type.Decimal: return Type.Decimal;
                    case Type.Money: return Type.Money;
                }
            }
            if (leftType == Type.Money)
            {
                return Type.Money;
            }
            return Type.Undefined;
        }
    }
}
