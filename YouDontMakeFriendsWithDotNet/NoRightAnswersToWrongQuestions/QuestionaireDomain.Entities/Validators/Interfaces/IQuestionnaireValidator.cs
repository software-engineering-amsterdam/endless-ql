using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Validators.Interfaces
{
    public interface IQuestionnaireValidator
    {
        IList<ValidationMetaData> Results { get; set; }
        void Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }
}