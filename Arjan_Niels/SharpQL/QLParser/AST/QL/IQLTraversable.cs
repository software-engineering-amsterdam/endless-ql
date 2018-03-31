namespace QLParser.AST.QL
{
    public interface IQLTraversable
    {
        void Accept(IQLVisitor visitor);
    }
}
