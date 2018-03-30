using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class UndefinedVariableValidator : IUndefinedVariableValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public UndefinedVariableValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var variableNodes = m_domainItemLocator
                .GetAll<IVariableNode>();

            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .ToList();

            foreach (var variableNode in variableNodes)
            {
                var nameIsDefined = questionNodes
                    .Any(x => x.QuestionName == variableNode.VariableName);

                if (!nameIsDefined)
                {
                    yield return new UndefinedVariableValidationMetaData
                    {
                        Message = $"The variable '{variableNode.VariableName}' has not been defined",
                        Source = m_domainItemLocator.GetRef<IVariableNode>(variableNode.Id)
                    };
                }
            }
        }
    }
}