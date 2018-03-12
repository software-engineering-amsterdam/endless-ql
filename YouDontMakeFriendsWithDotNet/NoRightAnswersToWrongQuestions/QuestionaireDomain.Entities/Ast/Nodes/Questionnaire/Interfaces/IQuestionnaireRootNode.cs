using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IQuestionnaireRootNode : INonTerminal
    {
        string QuestionnaireName { get; }
        IEnumerable<Reference<IStatementNode>> Statements { get; }
    }
}