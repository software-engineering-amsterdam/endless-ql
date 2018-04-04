using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    public class QuestionnaireTypeChecker : IQuestionnaireTypeChecker
    {
        private readonly List<IQuestionnairValidator> m_validators
            = new List<IQuestionnairValidator>();

        public QuestionnaireTypeChecker(
            IDuplicateVariableValidator duplicateVariableValidator,
            IUndefinedVariableValidator undefinedVariableValidator,
            IBooleanConditionValidator booleanConditionValidator,
            IDateComparisonValidator dateComparisonValidator,
            ITextComparisonValidator textComparisonValidator,
            IMathComparisonValidator mathComparisonValidator,
            IMathExpressionValidator mathExpressionValidator,
            IUnknownTypeValidator unknownTypeValidator,
            IDuplicateTextValidator duplicateTextValidator,
            ICyclicDependencyValidator cyclicDependencyValidator)
        {
            m_validators.Add(duplicateVariableValidator);
            m_validators.Add(undefinedVariableValidator);
            m_validators.Add(booleanConditionValidator);
            m_validators.Add(dateComparisonValidator);
            m_validators.Add(textComparisonValidator);
            m_validators.Add(mathComparisonValidator);
            m_validators.Add(mathExpressionValidator);
            m_validators.Add(unknownTypeValidator);
            m_validators.Add(duplicateTextValidator);
            m_validators.Add(cyclicDependencyValidator);
        }

        public IList<ValidationMetaData> Results { get; set; }
            = new List<ValidationMetaData>();

        public bool Validate(DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            foreach (var validator in m_validators)
                Results = Results
                    .Concat(validator.Validate(questionnaireRootNode))
                    .ToList();

            return Results.All(x => x.Severity != Severity.Error);
        }
    }
}