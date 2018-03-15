using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IOutputItemFactory
    {
        Reference<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(
            string displayName,
            IList<Reference<IQuestionOutputItem>> questions);

        Reference<IQuestionOutputItem> CreateQuestionOutputItem(
            string text,
            string value,
            Type type,
            bool isVisible,
            bool isReadonly);
   }
}
