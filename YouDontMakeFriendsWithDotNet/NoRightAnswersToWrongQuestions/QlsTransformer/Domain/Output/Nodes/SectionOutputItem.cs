using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Output.Nodes
{
    internal class SectionOutputItem : ISectionOutputItem
    {
        public SectionOutputItem(Guid id, string displayName)
        {
            Id = id;
            DisplayName = displayName;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public IList<DomainId<IStyledQuestionOutputItem>> Questions { get; set; }
    }
}