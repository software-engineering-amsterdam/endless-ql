using System;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST
{
    public class NormalQuestion : Question
    {
        public IValue Answer { get; }

        public NormalQuestion(int lineNumber, string id, string label, Type type, IValue answer) : base(lineNumber, id, label, type)
        {
            Answer = answer;
        }

        public NormalQuestion(int lineNumber, string id, string label, Type type) : this(lineNumber, id, label, type, NewUndefined(type)) { }

        private static IValue NewUndefined(Type type)
        {
            switch (type)
            {
                case Type.Boolean:
                    return new QLBoolean();
                case Type.Integer:
                    return new QLInteger();
                case Type.String:
                    return new QLString();
                case Type.Date:
                    return new QLDate();
                case Type.Decimal:
                    return new QLDecimal();
                case Type.Money:
                    return new QLMoney();
                case Type.Undefined:
                default:
                    throw new Exception("Unable to create a value of type: " + type);
            }
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
