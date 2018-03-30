using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class MathExpressionValidator : ComparisonValidator, IMathExpressionValidator
    {
        public MathExpressionValidator(
            IDomainItemLocator domainItemLocator)
            : base(domainItemLocator)
        {
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            return Validate<ICalculationVariableNode, MathExpressionValidationMetaData>(
                questionnaireRootNode,
                x => x != typeof(int) && x != typeof(decimal),
                @"calculation",
                @"number");
        }
    }
}