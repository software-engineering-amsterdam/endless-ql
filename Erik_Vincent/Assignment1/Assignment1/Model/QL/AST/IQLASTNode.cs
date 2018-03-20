namespace Assignment1.Model.QL.AST
{
    public interface IQLASTNode
    {
        void Accept(IQLASTVisitor visitor);
    }
}
