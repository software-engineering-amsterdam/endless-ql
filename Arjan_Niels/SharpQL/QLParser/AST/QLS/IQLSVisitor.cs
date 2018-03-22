namespace QLParser.AST.QLS
{
    public interface IQLSVisitor
    {
        void Visit(QLSNode node);
    }
}
