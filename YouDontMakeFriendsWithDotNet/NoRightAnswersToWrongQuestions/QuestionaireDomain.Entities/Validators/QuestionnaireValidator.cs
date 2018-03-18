using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Validators
{
    public class QuestionnaireValidator : IQuestionnaireValidator
    {
        private List<IValidator> validators = new List<IValidator>();
        public IList<ValidationMetaData> Results { get; set; } = new List<ValidationMetaData>();

        public QuestionnaireValidator(
            IDuplicateVariableValidator duplicateVariableValidator)
        {
            validators.Add(duplicateVariableValidator);
        }
        
        public void Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            foreach (var validator in validators)
            {
                Results = Results
                    .Concat(validator.Validate(questionnaireRootNode))
                    .ToList();
            }
        }
    }
}
