using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsTransformer.Domain.Output.Nodes
{
    public interface IStyledQuestionnaireOutputItem : IDomainItem
    {
        IList<DomainId<IPagesOutputItem>> Pages { get; set; }
    }
}