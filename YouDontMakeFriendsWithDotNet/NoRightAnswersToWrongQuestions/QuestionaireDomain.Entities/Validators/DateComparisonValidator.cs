using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class DateComparisonValidator : ComparisonValidator, IDateComparisonValidator
    {
        public DateComparisonValidator(
            IDomainItemLocator domainItemLocator)
            : base(domainItemLocator)
        {
        }

        public IEnumerable<ValidationMetaData> Validate(
            DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            return Validate<IDateVariableNode, DateComparisonValidationMetaData>(
                questionnaireRootNode,
                x => x != typeof(DateTime),
                @"date comparison",
                @"date");
        }
    }
}