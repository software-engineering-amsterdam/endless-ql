using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IOutputItemFactory
    {
        Reference<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(
            Reference<IQuestionnaireRootNode> variable,
            string displayName,
            IList<Reference<IQuestionOutputItem>> questions);

        Reference<IQuestionOutputItem> CreateQuestionOutputItem(
            Reference<IQuestionNode> variable,
            string text,
            string value,
            Type type,
            bool isVisible,
            bool isReadonly);
   }
}
