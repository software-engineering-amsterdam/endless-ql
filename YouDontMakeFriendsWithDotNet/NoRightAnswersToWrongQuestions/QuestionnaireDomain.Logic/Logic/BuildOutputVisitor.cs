using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using QuestionaireDomain.Entities;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class BuildOutputVisitor : 
        IBuildOutputVisitor
    {
        //ToDo, make a stack of visibility
        private bool m_questionsCurrentlyVisible = true;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IOutputItemFactory m_outputItemFactory;
        private readonly ISymbolTable<bool> m_boolLookup;
        private readonly ISymbolTable<decimal> m_decimalLookup;
        private readonly ISymbolTable<string> m_stringLookup;
        private readonly ISymbolTable<DateTime> m_dateLookup;
        private readonly IBooleanLogicVisitor m_booleanEvaluator;

        private readonly IList<Reference<IQuestionOutputItem>> questions = 
            new List<Reference<IQuestionOutputItem>>();

        public BuildOutputVisitor(
            IDomainItemLocator domainItemLocator,
            IOutputItemFactory outputItemFactory,
            ISymbolTable<bool> boolLookup,
            ISymbolTable<decimal> decimalLookup,
            ISymbolTable<string> stringLookup,
            ISymbolTable<DateTime> dateLookup,
            IBooleanLogicVisitor booleanEvaluator)
        {
            m_domainItemLocator = domainItemLocator;
            m_outputItemFactory = outputItemFactory;
            m_boolLookup = boolLookup;
            m_decimalLookup = decimalLookup;
            m_stringLookup = stringLookup;
            m_dateLookup = dateLookup;
            m_booleanEvaluator = booleanEvaluator;
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

            var statements = node.Statements;
            HandleStatements(statements);
            
            m_outputItemFactory.CreateQuestionnaireOutputItem(
                node.QuestionnaireName,
                questions);
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

            m_outputItemFactory.CreateQuestionOutputItem(
                node.QuestionText,
                GetValue(node.Id),
                node.QuestionType,
                m_questionsCurrentlyVisible,
                false);
        }

        private void Visit(Reference<IConditionalStatementNode> ifElseNode)
        {
            var node = ifElseNode.ToDomainItem(m_domainItemLocator);
            m_questionsCurrentlyVisible = Evaluate(node.Predicate);
            HandleStatements(node.Consequent);
            m_questionsCurrentlyVisible = !m_questionsCurrentlyVisible;
            HandleStatements(node.Alternative);
        }

        private bool Evaluate(Reference<IBooleanLogicNode> predicate)
        {
            return m_booleanEvaluator.Evaluate(predicate);
        }


        private string GetValue(Guid questionId)
        {
            var type = GetQuestionType(questionId);
            if (type == typeof(bool))
            {
                return m_boolLookup.Lookup(questionId).ToString();
            }

            if (type == typeof(string))
            {
                return m_stringLookup.Lookup(questionId) ?? "";
            }

            if (type == typeof(decimal) || type == typeof(int))
            {
                return m_decimalLookup.Lookup(questionId).ToString(CultureInfo.InvariantCulture);
            }

            if (type == typeof(DateTime))
            {
                return m_dateLookup.Lookup(questionId).ToString(CultureInfo.InvariantCulture);
            }

            throw new ArgumentException($"value lookup for type '{type}' not implemented");
        }


        private Type GetQuestionType(Guid questionId)
        {
            return m_domainItemLocator
                .Get<IQuestionNode>(questionId)
                ?.QuestionType;
        }

        public Reference<IQuestionOutputItem> Visit(Reference<IUserInputQuestionNode> node)
        {
            var domainItem = node.ToDomainItem(m_domainItemLocator);
            return m_outputItemFactory.CreateQuestionOutputItem(
                domainItem.QuestionText,
                GetValue(node.Id),
                GetQuestionType(node.Id),
                m_questionsCurrentlyVisible,
                false);
        }
    }
}