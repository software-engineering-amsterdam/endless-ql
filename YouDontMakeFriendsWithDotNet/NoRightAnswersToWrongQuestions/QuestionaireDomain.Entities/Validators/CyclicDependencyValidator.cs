using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class CyclicDependencyValidator : ICyclicDependencyValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ICalculationService m_calculationService;

        public CyclicDependencyValidator(
            IDomainItemLocator domainItemLocator,
            ICalculationService calculationService)
        {
            m_domainItemLocator = domainItemLocator;
            m_calculationService = calculationService;
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var questionNodes = m_domainItemLocator
                .GetAll<ICalculatedQuestionNode>()
                .ToList();

            foreach (var questionNode in questionNodes)
            {
                var variables = m_calculationService
                    .GetVariables(questionNode.CalculatedValue);

                if (variables.Contains(questionNode))
                {
                    yield return new CyclicDependencyValidationMetaData
                    {
                        Message = $"a cirular dependency was found",
                        Source = m_domainItemLocator.GetRef<IQuestionNode>(questionNode.Id)
                    };
                }
            }
        }
    }
}