﻿using System;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using IBinaryExpressionNode = QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces.IBinaryExpressionNode;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class BooleanEvaluatorVisitor : IBooleanEvaluatorVisitor
    {
        private readonly ICalculationVisitor m_calculationVisitor;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ISymbolTable m_lookup;

        public BooleanEvaluatorVisitor(
            IDomainItemLocator domainItemLocator,
            ICalculationVisitor calculationVisitor,
            ISymbolTable lookup)
        {
            m_domainItemLocator = domainItemLocator;
            m_calculationVisitor = calculationVisitor;
            m_lookup = lookup;
        }

        public bool Evaluate(DomainId<IBooleanLogicNode> predicate)
        {
            var node = m_domainItemLocator.Get<IBooleanLogicNode>(predicate.Id);
            dynamic d = node;
            return this.Evaluate(d);
        }

        public bool Evaluate(IBooleanVariableNode node)
        {
            return m_lookup.Lookup<bool>(GetQuestionId(node));
        }

        public string Evaluate(ITextVariableNode node)
        {
            return m_lookup.Lookup<string>(GetQuestionId(node));
        }

        public DateTime Evaluate(IDateVariableNode node)
        {
            return m_lookup.Lookup<DateTime>(GetQuestionId(node));
        }

        public decimal Evaluate(ICalculationVariableNode node)
        {
            return m_lookup.Lookup<decimal>(GetQuestionId(node));
        }

        private Guid GetQuestionId(IVariableNode variableNodeId)
        {
            var question = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .FirstOrDefault(x => x.QuestionName == variableNodeId.VariableName);

            if (question == null || !m_lookup.Exists(question.Id))
                throw new ArgumentException($@"variable node '{variableNodeId.DisplayName}' variable not initialized");

            return question.Id;
        }

        public object Evaluate(IUntypedVariableNode node)
        {
            var question = m_domainItemLocator
                .GetAll<IQuestionNode>()
                .FirstOrDefault(x => x.QuestionName == node.VariableName);

            if (question == null || !m_lookup.Exists(question.Id))
                throw new ArgumentException($@"untyped '{node.DisplayName}' variable not initialized");

            return m_lookup.Lookup(question.Id);
        }

        public bool Evaluate(IBooleanLiteralNode node)
        {
            return node.Value;
        }

        public bool Evaluate(IAndNode node)
        {
            return EvaluateLeft(node) && EvaluateRight(node);
        }

        public bool Evaluate(IOrNode node)
        {
            return EvaluateLeft(node) || EvaluateRight(node);
        }

        public bool Evaluate(INegateNode node)
        {
            var expresion = node.Expression.ToDomainItem(m_domainItemLocator);
            dynamic d = expresion;
            return !Evaluate(d);
        }

        public DateTime Evaluate(IDateNode node)
        {
            return node.Value;
        }

        public string Evaluate(ITextNode node)
        {
            return node.Value;
        }

        public bool Evaluate(IEqualityNode node)
        {
            return EvaluateLeft(node) == EvaluateRight(node);
        }

        public bool Evaluate(IInequalityNode node)
        {
            return EvaluateLeft(node) != EvaluateRight(node);
        }

        public bool Evaluate(IGreaterThanNode node)
        {
            return EvaluateLeft(node) > EvaluateRight(node);
        }

        public bool Evaluate(IGreaterOrEqualNode node)
        {
            return EvaluateLeft(node) >= EvaluateRight(node);
        }

        public bool Evaluate(ILessThanNode node)
        {
            return EvaluateLeft(node) < EvaluateRight(node);
        }

        public bool Evaluate(ILessOrEqualNode node)
        {
            return EvaluateLeft(node) <= EvaluateRight(node);
        }

        public decimal Evaluate(ICalculationNode node)
        {
            return m_calculationVisitor.Calculate(new DomainId<ICalculationNode>(node.Id));
        }

        private dynamic EvaluateRight(IBinaryExpressionNode node)
        {
            var right = node.RightExpression.ToDomainItem(m_domainItemLocator);
            dynamic dr = right;
            return Evaluate(dr);
        }

        private dynamic EvaluateLeft(IBinaryExpressionNode node)
        {
            var left = node.LeftExpression.ToDomainItem(m_domainItemLocator);
            dynamic dl = left;
            return Evaluate(dl);
        }

        private dynamic EvaluateRight(IRelationalLogicNode node)
        {
            var right = node.RightExpression.ToDomainItem(m_domainItemLocator);
            dynamic dr = right;
            return Evaluate(dr);
        }

        private dynamic EvaluateLeft(IRelationalLogicNode node)
        {
            var left = node.LeftExpression.ToDomainItem(m_domainItemLocator);
            dynamic dl = left;
            return Evaluate(dl);
        }
    }
}