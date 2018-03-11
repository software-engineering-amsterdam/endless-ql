using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireDomain.Entities;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class BuildOutputVisitor : 
        IBuildOutputVisitor,
        IAstToOutputVisitor,
        IAstToOutputVisitor<IQuestionnaireRootNode, IQuestionnaireOutputItem>,
        IAstToOutputVisitor<IUserInputQuestionNode, IQuestionOutputItem>

    {
        private bool m_questionsCurrentlyVisible = true;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IOutputItemFactory m_outputItemFactory;
        private readonly ISymbolTable<bool> m_boolLookup;
        private readonly ISymbolTable<decimal> m_decimalLookup;
        private readonly ISymbolTable<string> m_stringLookup;
        private readonly ISymbolTable<DateTime> m_dateLookup;

        public BuildOutputVisitor(
            IDomainItemLocator domainItemLocator,
            IOutputItemFactory outputItemFactory,
            ISymbolTable<bool> boolLookup,
            ISymbolTable<decimal> decimalLookup,
            ISymbolTable<string> stringLookup,
            ISymbolTable<DateTime> dateLookup)
        {
            m_domainItemLocator = domainItemLocator;
            m_outputItemFactory = outputItemFactory;
            m_boolLookup = boolLookup;
            m_decimalLookup = decimalLookup;
            m_stringLookup = stringLookup;
            m_dateLookup = dateLookup;
        }

        public Reference<IQuestionnaireOutputItem> Build(
            Reference<IQuestionnaireRootNode> node)
        {
            dynamic d = node;
            return this.Visit(d);
        }

        public Reference<IQuestionnaireOutputItem> Visit(
            Reference<IQuestionnaireRootNode> questionnaireNode)
        {
            var node = questionnaireNode
                .ToDomainItem(m_domainItemLocator);

            var questions = new List<Reference<IQuestionOutputItem>>();
            foreach (var statement in node.Statements)
            {
                if (m_domainItemLocator.Exists<IUserInputQuestionNode>(statement.Id))
                {
                    var domainItem = m_domainItemLocator
                        .GetRef<IUserInputQuestionNode>(statement.Id);
                    var questionRef = Visit(domainItem);
                    questions.Add(questionRef);
                }
            }

            return m_outputItemFactory.CreateQuestionnaireOutputItem(
                node.QuestionnaireName,
                questions);
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
                return m_decimalLookup.Lookup(questionId).ToString();
            }

            if (type == typeof(DateTime))
            {
                return m_dateLookup.Lookup(questionId).ToString();
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