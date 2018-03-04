using System.IO;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireOrchestration.Visitors
{
    public interface IBooleanLogicPrinter : IPrinter<IBooleanLogicNode>
    {
    }
}