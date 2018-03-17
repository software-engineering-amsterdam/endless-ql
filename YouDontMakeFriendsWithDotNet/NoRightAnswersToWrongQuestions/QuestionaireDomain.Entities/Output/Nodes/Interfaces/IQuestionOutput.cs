using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Nodes.Interfaces
{
    //ToDo: fix this to the available types (in the class)

    public interface IQuestionOutputItem : IOutputItem
    {
        Reference<IQuestionNode> Variable { get; }
        string QuestionText { get; }
        bool Visible { get; set; }
        bool ReadOnly { get; }
        Type QuestionType { get; }
        string Value { get; set; }
    }
}