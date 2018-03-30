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
            DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var questionNodes = m_domainItemLocator
                .GetAll<ICalculatedQuestionNode>()
                .ToList();

            foreach (var questionNode in questionNodes)
            {
                var noUsedVariables = new List<string>();
                if (IsCyclic(noUsedVariables, questionNode))
                {
                    yield return new CyclicDependencyValidationMetaData
                    {
                        Message = $"a cirular dependency was found",
                        Source = m_domainItemLocator.GetRef<IQuestionNode>(questionNode.Id)
                    };
                }
            }
        }

        // a recursive routine that walks the calculation variables until
        // it hits itsself.  only looking at variables in branch
        private bool IsCyclic(
            IEnumerable<string> usedVariableNames, 
            ICalculatedQuestionNode currentNode)
        {
            if (usedVariableNames.Contains(currentNode.QuestionName))
            {
                return true;
            }

            var variablesInCalculation = m_calculationService
                .GetVariables(currentNode.CalculatedValue)
                .OfType<ICalculatedQuestionNode>()
                .ToList();

            var expandedVariableNameList = usedVariableNames
                .Concat(new[] {currentNode.QuestionName})
                .ToList();

            foreach (var nextNode in variablesInCalculation)
            {
                if (IsCyclic(expandedVariableNameList, nextNode))
                {
                    return true;
                }
            }

            return false;
        }
    }
}