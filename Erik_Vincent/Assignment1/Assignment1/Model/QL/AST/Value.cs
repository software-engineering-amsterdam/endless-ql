using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST
{
    public abstract class Value : IExpression
    {
        public Type Type { get; }

        protected Value(Type type)
        {
            Type = type;
        }

        public abstract void Accept(IExpressionVisitor visitor);
    }
}
