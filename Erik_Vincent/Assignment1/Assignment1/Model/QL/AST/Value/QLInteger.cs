using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLInteger : IValue
    {
        public int Value { get; }

        public QLInteger(int value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
