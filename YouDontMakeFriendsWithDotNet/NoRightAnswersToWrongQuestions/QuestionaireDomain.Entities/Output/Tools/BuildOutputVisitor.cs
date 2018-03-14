using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class BuildOutputVisitor : 
        IBuildOutputVisitor
    {
        private Stack<bool> m_questionsCurrentlyVisible = new Stack<bool>() ;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IOutputItemFactory m_outputItemFactory;
        private readonly ISymbolTable m_lookup;
        private readonly IBooleanEvaluatorVisitor m_booleanEvaluator;

        private readonly IList<Reference<IQuestionOutputItem>> m_questions = 
            new List<Reference<IQuestionOutputItem>>();

        public BuildOutputVisitor(
            IDomainItemLocator domainItemLocator,
            IOutputItemFactory outputItemFactory,
            ISymbolTable lookup,
            IBooleanEvaluatorVisitor booleanEvaluator)
        {
            m_domainItemLocator = domainItemLocator;
            m_outputItemFactory = outputItemFactory;
            m_lookup = lookup;
            m_booleanEvaluator = booleanEvaluator;
            m_questionsCurrentlyVisible.Push(true);
        }

        public Reference<IQuestionnaireOutputItem> Build(
            Reference<IQuestionnaireRootNode> node)
        {
            dynamic d = node;
            this.Visit(d);
            return m_domainItemLocator
                .GetAllRefs<IQuestionnaireOutputItem>()
                .FirstOrDefault();
        }

        public void Visit(Reference<IQuestionnaireRootNode> questionnaireNode)
        {
            var node = questionnaireNode
                .ToDomainItem(m_domainItemLocator);
            
            HandleStatements(node.Statements);
            
            m_outputItemFactory.CreateQuestionnaireOutputItem(
                node.QuestionnaireName,
                m_questions);
        }

        private void HandleStatements(IEnumerable<Reference<IStatementNode>> statements)
        {
            foreach (var statement in statements)
            {
                if (m_domainItemLocator.Exists<IQuestionNode>(statement.Id))
                {
                    Visit(new Reference<IQuestionNode>(statement.Id));
                }
                else if (m_domainItemLocator.Exists<IConditionalStatementNode>(statement.Id))
                {
                    Visit(new Reference<IConditionalStatementNode>(statement.Id));
                }
            }
        }

        private void Visit(Reference<IQuestionNode> questionNode)
        {
            var node = questionNode.ToDomainItem(m_domainItemLocator);
            var temp = GetValue(node);
            var question = m_outputItemFactory.CreateQuestionOutputItem(
                node.QuestionText,
                GetValue(node),
                node.QuestionType,
                m_questionsCurrentlyVisible.Peek(),
                false);

            m_questions.Add(question);
        }

        private void Visit(Reference<IConditionalStatementNode> ifElseNode)
        {
            var node = ifElseNode.ToDomainItem(m_domainItemLocator);
            var predicateResult = Evaluate(node.Predicate);
            m_questionsCurrentlyVisible.Push(predicateResult);
            HandleStatements(node.Consequent);
            m_questionsCurrentlyVisible.Pop();
            m_questionsCurrentlyVisible.Push(!predicateResult);
            HandleStatements(node.Alternative);
            m_questionsCurrentlyVisible.Pop();
        }

        private bool Evaluate(Reference<IBooleanLogicNode> predicate)
        {
            return m_booleanEvaluator.Evaluate(predicate);
        }
        
        private string GetValue(IQuestionNode question)
        {
            var type = GetQuestionType(question.Id);
            if (type == typeof(bool))
            {
                return m_lookup.Lookup<bool>(question.Id).ToString();
            }

            if (type == typeof(string))
            {
                return m_lookup.Lookup<string>(question.Id) ?? "";
            }

            if (type == typeof(decimal))
            {
                return m_lookup.Lookup<decimal>(question.Id).ToString(CultureInfo.InvariantCulture);
            }

            if (type == typeof(int))
            {
                return m_lookup.Lookup<int>(question.Id).ToString(CultureInfo.InvariantCulture);
            }

            if (type == typeof(DateTime))
            {
                return m_lookup.Lookup<DateTime>(question.Id).ToString(CultureInfo.InvariantCulture);
            }

            throw new ArgumentException($@"value lookup for type '{type}' not implemented");
        }


        private Type GetQuestionType(Guid questionId)
        {
            return m_domainItemLocator
                .Get<IQuestionNode>(questionId)
                ?.QuestionType;
        }

        //public Reference<IQuestionOutputItem> Visit(Reference<IUserInputQuestionNode> node)
        //{
        //    var domainItem = node.ToDomainItem(m_domainItemLocator);
        //    return m_outputItemFactory.CreateQuestionOutputItem(
        //        domainItem.QuestionText,
        //        GetValue(node.Id),
        //        GetQuestionType(node.Id),
        //        m_questionsCurrentlyVisible,
        //        false);
        //}
    }
}