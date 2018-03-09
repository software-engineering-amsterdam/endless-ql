using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Questionnaire
{
    public interface IQuestionnaireRootNode : INonTerminal
    {
        string QuestionnaireName { get; }
        IEnumerable<Reference<IStatementNode>> Statements { get; }
    }
}