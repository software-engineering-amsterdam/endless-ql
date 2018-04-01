using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Nodes
{
    internal class QuestionnaireOutputItem : IQuestionnaireOutputItem
    {
        public QuestionnaireOutputItem(DomainId<IQuestionnaireRootNode> variable, Guid id, string displayName)
        {
            Variable = variable;
            Id = id;
            DisplayName = displayName;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public DomainId<IQuestionnaireRootNode> Variable { get; }
        public IList<DomainId<IQuestionOutputItem>> Questions { get; set; }
    }
}