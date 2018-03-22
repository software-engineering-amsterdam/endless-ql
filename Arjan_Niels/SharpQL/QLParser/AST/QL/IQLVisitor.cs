namespace QLParser.AST.QL
{
    public interface IQLVisitor
    {
        void Visit(QLNode node);
        void Visit(QuestionNode node);
        void Visit(ComputedNode node);
        void Visit(FormNode node);
    }
}
