using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST
{
    public class Undefined : Value
    {
        public Undefined(Type type) : base(type) { }

        public override void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
