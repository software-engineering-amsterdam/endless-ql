using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    public class DuplicateVariableValidator : IDuplicateVariableValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public DuplicateVariableValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .ToList();

            var questionNameAndTypes = questionNodes
                .Select(x => new { x.QuestionName, x.QuestionType })
                .ToList();

            foreach (var questionNode in questionNodes)
            {
                var mismatchCount = questionNameAndTypes
                    .Count(x =>
                        x.QuestionName == questionNode.QuestionName
                        && x.QuestionType != questionNode.QuestionType);

                if (mismatchCount > 1)
                {
                    var validationData = new DuplicateVariableValidationMetaData
                    {
                        Source = m_domainItemLocator.GetRef<IQuestionNode>(questionNode.Id),
                        Message = $@"The Question identifier '{questionNode.QuestionName}' is used multiple times with different types",
                    };

                    yield return validationData;
                }
            }
        }
    }
}