using System.Collections.Generic;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IOutputItemFactory
    {
        Reference<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(
            string displayName,
            IList<IQuestionOutputItem> questions);

        IQuestionOutputItem<T> CreateQuestionOutputItem<T>(
            string text,
            T value,
            bool isVisible,
            bool isReadonly);
   }
}
