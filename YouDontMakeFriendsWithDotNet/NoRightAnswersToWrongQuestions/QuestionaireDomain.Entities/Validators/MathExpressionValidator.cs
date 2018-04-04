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
            DomainId<IQuestionnaireRootNode> rootNode)
        {
            return Validate<ICalculationVariableNode, MathExpressionValidationMetaData>(
                rootNode,
                x => !x.IsNumeric(),
                @"calculation",
                @"number");
        }
    }
}