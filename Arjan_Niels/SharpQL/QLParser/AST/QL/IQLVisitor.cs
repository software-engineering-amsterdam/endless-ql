using QLParser.AST.QL.ExpressionNodes;

namespace QLParser.AST.QL
{
    public interface IQLVisitor
    {
        void Visit(QLCollectionNode node);
        void Visit(QuestionNode node);
        void Visit(ComputedNode node);
        void Visit(FormNode node);
        void Visit(ExpressionNode node);
        void Visit(ConditionalNode node);
    }
}
