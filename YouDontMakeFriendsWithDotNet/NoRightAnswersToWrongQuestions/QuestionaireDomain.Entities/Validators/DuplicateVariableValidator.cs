using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;

namespace QuestionnaireDomain.Entities.Validators
{
    public class DuplicateVariableValidator : IDuplicateVariableValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public DuplicateVariableValidator(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var questions = m_domainItemLocator
                .GetAll<IQuestionNode>();
            var questionNodes = questions.ToList();
            var questionNames = questionNodes
                .Select(x => new { x.QuestionName, x.QuestionType})
                .ToList();
            foreach (var questionNode in questionNodes)
            {
                var mismatchCount = questionNames
                    .Count(x => 
                        x.QuestionName == questionNode.QuestionName 
                        && x.QuestionType != questionNode.QuestionType);

                if (mismatchCount > 1)
                {
                    var validationData = new ValidationMetaData()
                    {
                        Source = new Reference<IQuestionNode>(questionNode.Id),
                        Message = $@"The Question identifier '{questionNode.QuestionName}' is used multiple times with different types",
                        Severity = Severity.Error
                    };

                    yield return validationData;
                }
            }
        }
    }
}