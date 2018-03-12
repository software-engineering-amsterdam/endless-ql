using System.IO;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;

namespace QuestionaireOrchestration.Visitors
{
    public interface IBooleanLogicPrinter : IPrinter<IBooleanLogicNode>
    {
    }
}