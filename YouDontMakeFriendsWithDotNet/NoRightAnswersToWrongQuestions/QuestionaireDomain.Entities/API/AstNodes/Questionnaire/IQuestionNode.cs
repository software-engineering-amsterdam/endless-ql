using System;

namespace QuestionaireDomain.Entities.API.AstNodes.Questionnaire
{
    public interface IQuestionNode : IStatementNode, ITerminal
    {
        string QuestionId { get; }
        string QuestionText { get; }
        Type QuestionType { get; }
    }
}