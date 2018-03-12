using System.IO;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;

namespace QuestionaireOrchestration.Visitors
{
    public interface IBooleanLogicPrinter : IPrinter<IBooleanLogicNode>
    {
    }
}