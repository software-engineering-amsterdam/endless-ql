using System;

namespace QuestionnaireDomain.Entities.Output.Nodes.Interfaces
{
    //ToDo: fix this to the available types (in the class)

    public interface IQuestionOutputItem : IOutputItem
    {
        string QuestionText { get; }
        bool Visible { get; set; }
        bool ReadOnly { get; }
        Type QuestionType { get; }
        string Value { get; set; }
    }
}