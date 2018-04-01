namespace Assignment1.Model.QL.RenderTree
{
    public interface IQuestionVisitor
    {
        void Visit(RenderableQuestionBool question);
        void Visit(RenderableQuestionInt question);
        void Visit(RenderableQuestionDate question);
        void Visit(RenderableQuestionDecimal question);
        void Visit(RenderableQuestionMoney question);
        void Visit(RenderableQuestionString question);
    }
}
