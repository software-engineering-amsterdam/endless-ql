using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface ICalculationVisitor
    {
        decimal Calculate(Reference<ICalculationNode> node);
    }
}