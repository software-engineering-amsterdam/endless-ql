using System;
using System.IO;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireOrchestration.Visitors
{
    public interface IPrinter<T> where T : IAstNode
    {
        void Print(T node);
        TextWriter Writer { get; set; }
    }

    public interface IQuestionnairePrinter : IPrinter<IQuestionnaireNode>
    {
    }
}
