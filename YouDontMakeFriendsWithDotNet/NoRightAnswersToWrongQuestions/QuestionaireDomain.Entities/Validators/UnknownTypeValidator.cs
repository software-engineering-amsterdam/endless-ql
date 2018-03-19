using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal class UnknownTypeValidator : IUnknownTypeValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public UnknownTypeValidator(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public IEnumerable<ValidationMetaData> Validate(
            Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var untypedOperands = m_domainItemLocator
                .GetAll<IUntypedVariableNode>()
                .Select(x => x.Id);

            var untypedOperators = m_domainItemLocator
                .GetAll<IRelationalLogicNode>()
                .Where(x => untypedOperands.Contains(x.LeftExpression.Id));

            foreach (var untypedOperator in untypedOperators)
            {
                var leftName = m_domainItemLocator
                    .Get<IUntypedVariableNode>(untypedOperator.LeftExpression.Id)
                    .VariableName;

                var rightName = m_domainItemLocator
                    .Get<IUntypedVariableNode>(untypedOperator.RightExpression.Id)
                    .VariableName;

                if (leftName == rightName)
                {
                    continue;
                }

                var leftType = m_domainItemLocator
                    .GetAll<IQuestionNode>()
                    .FirstOrDefault(x => x.QuestionName == leftName)
                    ?.QuestionType;

                var rightType = m_domainItemLocator
                    .GetAll<IQuestionNode>()
                    .FirstOrDefault(x => x.QuestionName == rightName)
                    ?.QuestionType;


                if (leftType != rightType)
                {
                    if ((leftType == typeof(int) || leftType == typeof(decimal)) && (rightType == typeof(int) || rightType == typeof(decimal)))
                    {
                        continue;
                    }

                    var leftTypeText = GetTypeDisplay(leftType);
                    var rightTypeText = GetTypeDisplay(rightType);

                    yield return new UnkownTypeExpressionValidationMetaData
                    {
                        Message =
                            $@"The expression '{untypedOperator.Definition}' contains the {leftTypeText} variable '{leftName}' and {rightTypeText} variable '{rightName}'. The types {leftTypeText} and {rightTypeText} cannot be compared.",
                        Source = m_domainItemLocator.GetRef<IRelationalLogicNode>(untypedOperator.Id)
                    };

                    continue;
                }

                if (leftType == typeof(bool) || leftType == typeof(string))
                {
                    var displayType = GetTypeDisplay(leftType);
                    if (!(untypedOperator is IEqualityNode) && !(untypedOperator is IInequalityNode))
                    {
                        yield return new UnkownTypeExpressionValidationMetaData
                        {
                            Message =
                                $@"The expression '{untypedOperator.Definition}' contains the {displayType} variables '{leftName}' and '{rightName}'. A {displayType} can only be compared for equality.",
                            Source = m_domainItemLocator.GetRef<IRelationalLogicNode>(untypedOperator.Id)
                        };
                    }
                }
            }
        }

        private string GetTypeDisplay(Type type)
        {
            if (type == typeof(bool))
            {
                return @"boolean";
            }
            if (type == typeof(int))
            {
                return @"integer";
            }
            if (type == typeof(string))
            {
                return @"string";
            }
            if (type == typeof(decimal))
            {
                return @"decimal";
            }

            if (type == typeof(DateTime))
            {
                return @"date";
            }

            throw new ArgumentException($@"unhandled type {type}");
        }
    }
}