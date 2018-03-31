using System;
using System.Collections.Generic;
using QuestionnaireOrchestration.Models;

namespace QlsTransformer.Orchestration.Models
{
    public class StyledQuestionnaireModel : DomainItemModel
    {
        public StyledQuestionnaireModel(
            Guid questionnaireId,
            string questionnaireDisplayName) 
            : base(questionnaireId, questionnaireDisplayName)
        {
            QuestionnaireId = questionnaireId;
            QuestionnaireDisplayName = questionnaireDisplayName;
        }

        public Guid QuestionnaireId { get; }
        public string QuestionnaireDisplayName { get; }
        public IList<PageModel> Pages { get; } = new List<PageModel>();
    }
}
