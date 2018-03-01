using System.Collections.Generic;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.API.AstNodes.Questionnaire
{
    public interface IConditionalStatementNode : IStatementNode, INonTerminal
    {
        string ConditionDefinition { get; }
        IBooleanLogicNode Predicate { get; }
        IList<IQuestionnaireNode> Consequent { get; }
        IList<IQuestionnaireNode> Alternative { get; }
    }
}