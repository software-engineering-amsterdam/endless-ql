namespace Assignment1.Model.QLS.AST
{
    public abstract class Statement : IQLSASTNode
    {
        public abstract void Accept(IQLSASTVisitor visitor);
    }
}
