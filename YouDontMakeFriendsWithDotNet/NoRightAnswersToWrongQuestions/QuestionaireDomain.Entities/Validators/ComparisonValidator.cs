using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators
{
    internal abstract class ComparisonValidator
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        protected ComparisonValidator(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        protected IEnumerable<ValidationMetaData> Validate<TNode, TData>(
            DomainId<IQuestionnaireRootNode> questionnaireRootNode,
            Func<IQuestionType, bool> correctTypePredicate,
            string comparisonType,
            string expectedType)
            where TNode : IVariableNode
            where TData : ValidationMetaData, new()
        {
            var booleanVariableNodes = m_domainItemLocator
                .GetAll<TNode>();

            var questionNodes = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .ToList();

            foreach (var variableNode in booleanVariableNodes)
            {
                var type = questionNodes
                    .FirstOrDefault(x => x.QuestionName == variableNode.VariableName)
                    ?.QuestionType;

                if (type == null || correctTypePredicate(type))
                    yield return new TData
                    {
                        Message =
                            $@"The variable '{variableNode.VariableName}' is in a {comparisonType} but is not a {
                                    expectedType
                                }, it is '{type}'",
                        Source = m_domainItemLocator.GetRef<TNode>(variableNode.Id)
                    };
            }
        }
    }
}