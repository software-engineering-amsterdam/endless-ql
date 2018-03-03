using System.IO;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireOrchestration.Visitors
{
    public interface IQuestionnairePrinter
    {
        void Print(IAstNode node);
        TextWriter Writer { get; set; }
    }
}
