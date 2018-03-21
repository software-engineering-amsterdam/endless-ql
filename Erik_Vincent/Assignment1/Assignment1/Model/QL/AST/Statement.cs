namespace Assignment1.Model.QL.AST
{
    public abstract class Statement : QLNode, IQLASTNode
    {
        public abstract void Accept(IQLASTVisitor visitor);
    }
}
