using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    public class DuplicateTextValidator : IDuplicateTextValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public DuplicateTextValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IQuestionnaireRootNode> rootNode)
        {
            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .ToList();

            var duplicateTexts = questionNodes
                .GroupBy(x => x.QuestionText)
                .Where(g => g.Count() > 1)
                .Select(y => y.Key)
                .ToList();

            foreach (var questionNode in questionNodes)
            {
                if (duplicateTexts.All(x => x != questionNode.QuestionText)) continue;

                var validationData = new DuplicateTextValidationMetaData
                {
                    Source = m_domainItemLocator.GetRef<IQuestionNode>(questionNode.Id),
                    Message = $@"The text '{questionNode.QuestionText}' is used multiple times"
                };

                yield return validationData;
            }
        }
    }
}