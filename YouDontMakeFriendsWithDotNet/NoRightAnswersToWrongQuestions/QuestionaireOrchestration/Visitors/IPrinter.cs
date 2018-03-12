using System.IO;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionaireOrchestration.Visitors
{
    public interface IPrinter<T> where T : IAstNode
    {
        void Print(T node);
        TextWriter Writer { get; set; }
    }
}