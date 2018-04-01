using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IOutputItemFactory
    {
        DomainId<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(
            DomainId<IQuestionnaireRootNode> variable,
            string displayName,
            IList<DomainId<IQuestionOutputItem>> questions);

        DomainId<IQuestionOutputItem> CreateQuestionOutputItem(
            DomainId<IQuestionNode> variable,
            string value,
            bool isVisible,
            bool isReadonly);
   }
}
