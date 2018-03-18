using System;

namespace QuestionnaireOrchestration.Models
{
    public class QuestionnaireDefinitionModel : DomainItemModel
    {
        public QuestionnaireDefinitionModel(Guid id, string name)
            : base(id, name)
        {
        }
    }
}