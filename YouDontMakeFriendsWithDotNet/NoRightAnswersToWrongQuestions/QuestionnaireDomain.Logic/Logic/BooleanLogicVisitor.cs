using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class BooleanLogicVisitor : IBooleanLogicVisitor
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public BooleanLogicVisitor(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
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
            var left = node.LeftExpression.ToDomainItem(m_domainItemLocator);
            dynamic dl = left;
            var right = node.RightExpression.ToDomainItem(m_domainItemLocator);
            dynamic dr = right;
            return Evaluate(dl) && Evaluate(dr);
        }

        public bool Evaluate(IOrNode node)
        {
            var left = node.LeftExpression.ToDomainItem(m_domainItemLocator);
            dynamic dl = left;
            var right = node.RightExpression.ToDomainItem(m_domainItemLocator);
            dynamic dr = right;
            return Evaluate(dl) || Evaluate(dr);
        }


        public bool Evaluate(INegateNode node)
        {
            var expresion = node.Expression.ToDomainItem(m_domainItemLocator);
            dynamic d = expresion;
            return !Evaluate(d);
        }
    }
}