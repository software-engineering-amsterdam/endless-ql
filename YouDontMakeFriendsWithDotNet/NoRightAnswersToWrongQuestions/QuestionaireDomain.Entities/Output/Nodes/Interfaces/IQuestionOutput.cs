using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Nodes.Interfaces
{
    public interface IQuestionOutputItem : IOutputItem
    {
        DomainId<IQuestionNode> Variable { get; }
        string QuestionName { get; }
        string QuestionText { get; }
        bool Visible { get; set; }
        bool ReadOnly { get; }
        IQuestionType QuestionType { get; }
        string Value { get; set; }
    }
}