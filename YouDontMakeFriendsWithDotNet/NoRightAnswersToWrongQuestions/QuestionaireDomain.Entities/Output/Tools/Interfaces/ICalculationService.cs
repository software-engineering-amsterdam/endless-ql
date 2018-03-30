using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface ICalculationService
    {
        void UpdateCalculations();
        IEnumerable<IQuestionNode> GetVariables(DomainId<ICalculationNode> node);
    }
}