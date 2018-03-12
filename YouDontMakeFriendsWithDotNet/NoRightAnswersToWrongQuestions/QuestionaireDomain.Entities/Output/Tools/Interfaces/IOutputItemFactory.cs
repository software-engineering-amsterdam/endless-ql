using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.API.Output;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.API
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
