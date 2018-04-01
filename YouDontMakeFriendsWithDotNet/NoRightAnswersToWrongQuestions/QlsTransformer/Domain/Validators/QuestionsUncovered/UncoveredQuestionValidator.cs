using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Validators.UnkownQuestion;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators.QuestionsUncovered
{
    public class UncoveredQuestionValidator : IUncoveredQuestionValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public UncoveredQuestionValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IStyleSheetRootNode> rootNode)
        {
            var qlsQuestionNames = m_domainItemLocator
                .GetAll<IQlsQuestionNode>()
                .Select(x => x.Name)
                .ToList();
            
            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .ToList();

            foreach (var questionNode in questionNodes)
            {
                if (qlsQuestionNames.Contains(questionNode.QuestionName))
                {
                    continue;
                }

                if (TypeHasDefault(questionNode))
                {
                    continue;
                }

                var validationData = new UncoveredQuestionValidationMetaData
                {
                    Source = m_domainItemLocator.GetRef<IQuestionOutputItem>(questionNode.Id),
                    Message = $@"The question '{questionNode.QuestionName}' does not have a style"
                };

                yield return validationData;
            }
        }

        private bool TypeHasDefault(IQuestionOutputItem questionNode)
        {
            var styleSheet = m_domainItemLocator
                .GetAll<IStyleSheetRootNode>()
                .FirstOrDefault();
            
            // ToDo: make this work
            foreach (var styleSheetPage in styleSheet.Pages)
            {
            }
            
            return true;
        }
    }
}