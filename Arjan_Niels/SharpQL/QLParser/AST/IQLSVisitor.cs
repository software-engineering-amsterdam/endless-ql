using QLParser.AST.QLS;

namespace QLParser.AST
{
    public interface IQLSVisitor
    {
        void Visit(QLSNode node);
    }
}
