namespace Assignment1.Model.QL.AST.Expression
{
    public abstract class Binary : ASTNode, IExpression
    {
        public IExpression Left { get; }
        public IExpression Right { get; }

        protected Binary(IExpression left, IExpression right)
        {
            Left = left;
            Right = right;
        }

        public abstract void Accept(IExpressionVisitor visitor);
    }
}
