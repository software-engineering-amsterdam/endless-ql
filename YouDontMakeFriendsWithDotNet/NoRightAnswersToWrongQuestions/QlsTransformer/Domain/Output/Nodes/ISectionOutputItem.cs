using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsTransformer.Domain.Output.Nodes
{
    public interface ISectionOutputItem : IDomainItem
    {
        IList<DomainId<IStyledQuestionOutputItem>> Questions { get; set; }
    }
}