namespace Assignment1.Model.QL.RenderTree
{
    public interface IQuestionVisitor
    {
        void Visit(QuestionBool question);
        void Visit(QuestionInt question);
        void Visit(QuestionDate question);
        void Visit(QuestionDecimal question);
        void Visit(QuestionMoney question);
        void Visit(QuestionString question);
    }
}
