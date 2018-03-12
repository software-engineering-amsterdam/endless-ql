using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Nodes.Interfaces
{
    public interface IQuestionnaireOutputItem : IOutputItem
    {
        IList<Reference<IQuestionOutputItem>> Questions { get; set; }
    }
}