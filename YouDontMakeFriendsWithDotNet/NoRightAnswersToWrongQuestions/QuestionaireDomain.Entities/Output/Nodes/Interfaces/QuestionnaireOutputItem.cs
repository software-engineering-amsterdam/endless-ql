using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Nodes.Interfaces
{
    internal class QuestionnaireOutputItem : IQuestionnaireOutputItem
    {
        public QuestionnaireOutputItem(Guid id, string displayName)
        {
            Id = id;
            DisplayName = displayName;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public IList<Reference<IQuestionOutputItem>> Questions { get; set; }
    }
}