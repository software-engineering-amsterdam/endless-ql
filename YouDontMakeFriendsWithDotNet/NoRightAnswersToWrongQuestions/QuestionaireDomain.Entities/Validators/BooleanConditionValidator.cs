using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class BooleanConditionValidator : ComparisonValidator, IBooleanConditionValidator
    {
        public BooleanConditionValidator(
            IDomainItemLocator domainItemLocator)
            : base(domainItemLocator)
        {
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            return Validate<IBooleanVariableNode, BooleanConditionValidationMetaData>(
                questionnaireRootNode,
                x => x != typeof(bool),
                @"condition",
                @"bool");
        }
    }
}