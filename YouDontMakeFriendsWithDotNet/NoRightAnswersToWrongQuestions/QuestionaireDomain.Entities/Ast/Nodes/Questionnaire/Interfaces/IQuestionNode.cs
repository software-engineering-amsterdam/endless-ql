namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IQuestionNode : IStatementNode, ITerminal
    {
        string QuestionName { get; }
        string QuestionText { get; }
        IQuestionType QuestionType { get; }
    }
}