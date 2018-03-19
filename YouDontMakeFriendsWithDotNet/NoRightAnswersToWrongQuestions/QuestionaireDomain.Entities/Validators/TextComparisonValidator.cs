using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class TextComparisonValidator : ITextComparisonValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public TextComparisonValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var textVariableNodes = m_domainItemLocator
                .GetAll<ITextVariableNode>();

            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .ToList();

            foreach (var variableNode in textVariableNodes)
            {
                var type = questionNodes
                    .FirstOrDefault(x => x.QuestionName == variableNode.VariableName)
                    ?.QuestionType;

                if (type == null || type != typeof(string))
                {
                    yield return new TextComparisonValidationMetaData
                    {
                        Message =
                            $"The variable '{variableNode.VariableName}' is in a string comparison but is not a string, it is '{type}'",
                        Source = m_domainItemLocator.GetRef<ITextVariableNode>(variableNode.Id)
                    };
                }
            }
        }
    }
}