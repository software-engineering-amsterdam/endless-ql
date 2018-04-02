using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Output.Nodes
{
    internal class PagesOutputItem : IPagesOutputItem
    {
        public PagesOutputItem(Guid id, string displayName)
        {
            Id = id;
            DisplayName = displayName;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public IList<DomainId<ISectionOutputItem>> Sections { get; set; }
    }
}