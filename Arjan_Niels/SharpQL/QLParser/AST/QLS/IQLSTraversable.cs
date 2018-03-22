namespace QLParser.AST.QLS
{
    public interface IQLSTraversable
    {
        void Accept(IQLSVisitor visitor);
    }
}
