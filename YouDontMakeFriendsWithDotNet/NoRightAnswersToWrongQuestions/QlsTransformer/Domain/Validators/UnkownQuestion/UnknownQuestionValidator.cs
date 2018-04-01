using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators.UnkownQuestion
{
    public class UnknownQuestionValidator : IUnknownQuestionValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public UnknownQuestionValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IStyleSheetRootNode> rootNode)
        {
            var qlsQuestionNodes = m_domainItemLocator
                .GetAll<IQlsQuestionNode>()
                .ToList();
            
            var questionNames = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Select(x => x.QuestionName)
                .ToList();

            foreach (var qlsQuestionNode in qlsQuestionNodes)
            {
                if (questionNames.Contains(qlsQuestionNode.Name))
                {
                    continue;
                }

                var validationData = new UnknownQuestionValidationMetaData
                {
                    Source = m_domainItemLocator.GetRef<IQlsQuestionNode>(qlsQuestionNode.Id),
                    Message = $@"The stylesheet question '{qlsQuestionNode.Name}' is not a valid question"
                };

                yield return validationData;
            }
        }
    }
}