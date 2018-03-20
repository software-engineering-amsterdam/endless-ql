using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IQuestionnaireRootNode : INonTerminal
    {
        string QuestionnaireName { get; }
        IEnumerable<Reference<IStatementNode>> Statements { get; }
    }
}