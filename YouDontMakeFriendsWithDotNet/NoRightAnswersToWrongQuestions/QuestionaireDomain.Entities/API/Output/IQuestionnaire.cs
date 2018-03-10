using System;
using System.Collections.Generic;

namespace QuestionaireDomain.Entities.API.Output
{
    public interface IOutputItem : IDomainItem
    { }

    public interface IQuestionnaireOutputItem : IOutputItem
    {
        IList<IQuestionOutputItem> Questions { get; set; }
    }

    internal class QuestionnaireOutputItem : IQuestionnaireOutputItem
    {
        public QuestionnaireOutputItem(Guid id, string displayName)
        {
            Id = id;
            DisplayName = displayName;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public IList<IQuestionOutputItem> Questions { get; set; }
    }
}
