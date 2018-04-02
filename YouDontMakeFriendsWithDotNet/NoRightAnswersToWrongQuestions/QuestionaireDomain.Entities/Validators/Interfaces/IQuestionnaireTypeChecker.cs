using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators.Interfaces
{
    public interface IQuestionnaireTypeChecker
    {
        IList<ValidationMetaData> Results { get; set; }

        bool Validate(
            DomainId<IQuestionnaireRootNode> questionnaireRootNode);
    }
}