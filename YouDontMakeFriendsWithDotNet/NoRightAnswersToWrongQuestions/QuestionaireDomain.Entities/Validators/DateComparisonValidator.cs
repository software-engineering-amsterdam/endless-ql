using System;
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
    internal class DateComparisonValidator : IDateComparisonValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public DateComparisonValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var booleanVariableNodes = m_domainItemLocator
                .GetAll<IDateVariableNode>();

            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .ToList();

            foreach (var variableNode in booleanVariableNodes)
            {
                var type = questionNodes
                    .FirstOrDefault(x => x.QuestionName == variableNode.VariableName)
                    ?.QuestionType;

                if (type == null || type != typeof(DateTime))
                {
                    yield return new DateComparisonValidationMetaData
                    {
                        Message =
                            $"The variable '{variableNode.VariableName}' is in a date comparison but is not a date, it is '{type}'",
                        Source = m_domainItemLocator.GetRef<IDateVariableNode>(variableNode.Id)
                    };
                }
            }
        }
    }
}