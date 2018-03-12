using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface ICalculationVisitor
    {
        decimal Calculate(Reference<ICalculationNode> node);
    }
}