namespace Assignment1.Model.QLS.AST
{
    public interface IQLSASTNode
    {
        void Accept(IQLSASTVisitor visitor);
    }
}
