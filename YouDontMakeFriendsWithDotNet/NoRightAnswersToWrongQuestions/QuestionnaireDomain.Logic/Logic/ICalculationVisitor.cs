using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface ICalculationVisitor
    {
        decimal Calculate(Reference<ICalculationNode> node);
    }
}