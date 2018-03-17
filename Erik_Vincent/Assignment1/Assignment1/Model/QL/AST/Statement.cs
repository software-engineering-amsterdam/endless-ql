namespace Assignment1.Model.QL.AST
{
    public abstract class Statement : IQLASTNode
    {
        public abstract void Accept(IQLASTVisitor visitor);
    }
}
