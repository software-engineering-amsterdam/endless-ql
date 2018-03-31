using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsTransformer.Domain.Output.Nodes
{
    public interface IPagesOutputItem : IDomainItem
    {
        IList<DomainId<ISectionOutputItem>> Sections { get; set; }
    }
}