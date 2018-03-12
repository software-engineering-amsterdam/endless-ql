using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;
using IBinaryExpressionNode = QuestionaireDomain.Entities.API.AstNodes.Boolean.IBinaryExpressionNode;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class BooleanLogicVisitor : IBooleanLogicVisitor
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ICalculationVisitor m_calculationVisitor;

        public BooleanLogicVisitor(
            IDomainItemLocator domainItemLocator,
            ICalculationVisitor calculationVisitor)
        {
            m_domainItemLocator = domainItemLocator;
            m_calculationVisitor = calculationVisitor;
        }

        public bool Evaluate(Reference<IBooleanLogicNode> predicate)
        {
            var node = m_domainItemLocator.Get<IBooleanLogicNode>(predicate.Id);
            dynamic d = node;
            return this.Evaluate(d);
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
            return m_calculationVisitor.Calculate(new Reference<ICalculationNode>(node.Id));
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