using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    public class QuestionnaireValidator : IQuestionnaireValidator
    {
        private readonly List<IValidator> m_validators 
            = new List<IValidator>();

        public IList<ValidationMetaData> Results { get; set; } 
            = new List<ValidationMetaData>();

        public QuestionnaireValidator(
            IDuplicateVariableValidator duplicateVariableValidator,
            IUndefinedVariableValidator undefinedVariableValidator,
            IBooleanConditionValidator booleanConditionValidator)
        {
            m_validators.Add(duplicateVariableValidator);
            m_validators.Add(undefinedVariableValidator);
            m_validators.Add(booleanConditionValidator);
        }
        
        public void Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            foreach (var validator in m_validators)
            {
                Results = Results
                    .Concat(validator.Validate(questionnaireRootNode))
                    .ToList();
            }
        }
    }
}