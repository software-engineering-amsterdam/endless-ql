using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class MathComparisonValidator : ComparisonValidator, IMathComparisonValidator
    {
        public MathComparisonValidator(
            IDomainItemLocator domainItemLocator)
            : base(domainItemLocator)
        {
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            return Validate<ICalculationVariableNode, MathComparisonValidationMetaData>(
                questionnaireRootNode,
                x => x != typeof(int) && x != typeof(decimal),
                @"number comparison",
                @"number");
        }



        //private readonly IDomainItemLocator m_domainItemLocator;

        //public MathComparisonValidator(
        //    IDomainItemLocator domainItemLocator)
        //{
        //    m_domainItemLocator = domainItemLocator;
        //}

        //public IEnumerable<ValidationMetaData> Validate(
        //    Reference<IQuestionnaireRootNode> questionnaireRootNode)
        //{
        //    var booleanVariableNodes = m_domainItemLocator
        //        .GetAll<ICalculationVariableNode>();

        //    var questionNodes = m_domainItemLocator
        //        .GetAll<IQuestionNode>()
        //        .ToList();

        //    foreach (var variableNode in booleanVariableNodes)
        //    {
        //        var type = questionNodes
        //            .FirstOrDefault(x => x.QuestionName == variableNode.VariableName)
        //            ?.QuestionType;

        //        if (type == null || (type != typeof(int) && type != typeof(decimal)))
        //        {
        //            yield return new MathComparisonValidationMetaData
        //            {
        //                Message =
        //                    $@"The variable '{variableNode.VariableName}' is in a number comparison but is not a number, it is '{type}'",
        //                Source = m_domainItemLocator.GetRef<ICalculationVariableNode>(variableNode.Id)
        //            };
        //        }
        //    }
        //}
    }
}