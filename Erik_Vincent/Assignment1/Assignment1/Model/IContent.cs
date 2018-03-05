namespace Assignment1.Model
{
    public interface IContentVisitor
    {
        void Visit(QuestionBool question);
        void Visit(QuestionInt question);
        void Visit(QuestionDate question);
        void Visit(QuestionDecimal question);
        void Visit(QuestionMoney question);
        void Visit(QuestionString question);
        void Visit(IfStatement ifStatement);
        void Visit(IfElseStatement ifElseStatement);
    }

    public abstract class Content
    {
        public abstract void Accept(IContentVisitor visitor);
    }
}
