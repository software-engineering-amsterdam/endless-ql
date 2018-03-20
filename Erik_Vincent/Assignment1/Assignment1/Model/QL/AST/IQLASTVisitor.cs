namespace Assignment1.Model.QL.AST
{
    public interface IQLASTVisitor
    {
        void Visit(QuestionForm questionForm);
        void Visit(NormalQuestion question);
        void Visit(ComputedQuestion question);
        void Visit(IfStatement ifStatement);
    }
}
