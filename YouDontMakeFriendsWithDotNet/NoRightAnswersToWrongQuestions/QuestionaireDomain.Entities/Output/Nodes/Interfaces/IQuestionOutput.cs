using System;
using QuestionnaireDomain.Entities.API.Output;

namespace QuestionnaireDomain.Entities.API
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