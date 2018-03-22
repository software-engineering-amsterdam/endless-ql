namespace QLParser.AST.QLS
{
    public interface IQLSVisitor
    {
        void Visit(QLSNode node);
        void Visit(QLSQuestionNode node);
        void Visit(QLSStructuralNode node);
    }
}
