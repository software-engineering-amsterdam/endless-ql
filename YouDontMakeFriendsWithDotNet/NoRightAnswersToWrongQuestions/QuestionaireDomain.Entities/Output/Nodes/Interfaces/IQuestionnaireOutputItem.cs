using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Nodes.Interfaces
{
    public interface IQuestionnaireOutputItem : IOutputItem
    {
        Reference<IQuestionnaireRootNode> Variable { get; }
        IList<Reference<IQuestionOutputItem>> Questions { get; set; }
    }
}