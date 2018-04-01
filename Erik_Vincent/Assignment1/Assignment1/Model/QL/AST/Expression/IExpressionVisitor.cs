using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST.Expression
{
    public interface IExpressionVisitor : IValueVisitor
    {
        void Visit(Not expression);
        void Visit(Reference expression);
        void Visit(And expression);
        void Visit(Or expression);
        void Visit(LessThan expression);
        void Visit(GreaterThan expression);
        void Visit(GreaterThanOrEqual expression);
        void Visit(LessThanOrEqual expression);
        void Visit(NotEqual expression);
        void Visit(Equal expression);
        void Visit(Add expression);
        void Visit(Subtract expression);
        void Visit(Multiply expression);
        void Visit(Divide expression);
    }
}
