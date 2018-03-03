using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireOrchestration.Visitors
{
    public class QuestionnairePrinter : IQuestionnairePrinter
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private IndentedTextWriter m_writer;

        public TextWriter Writer
        {
            get { return m_writer; }
            set { m_writer = new IndentedTextWriter(value); }
        }

        public QuestionnairePrinter(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }
        
        public void Print(IAstNode node)
        {
            dynamic d = node;
            this.Visit(d);
        }

        private void Visit(IRootNode node)
        {
            m_writer.WriteLine($"questionaire: {node.QuestionnaireName}");
            foreach (var statementId in node.Statements)
            {
                var statement = m_domainItemLocator.Get<IStatementNode>(statementId.Id);
                VisitSubExpression(statement);
            }
        }
        
        private void Visit(IUserInputQuestionNode question)
        {
            m_writer.WriteLine($"Question{{ Id: {question.QuestionId}, Type: {question.QuestionType}, Text: {question.QuestionText} }}");
        }
        
        private void Visit(IConditionalStatementNode conditionalStatement)
        {
            m_writer.WriteLine($"IF  ({conditionalStatement.ConditionDefinition})");
            m_writer.WriteLine("THEN");
            foreach (var statement in conditionalStatement.Consequent)
            {
                VisitSubExpression(statement.FromLocator(m_domainItemLocator));
            }

            if (!conditionalStatement.Alternative.Any())
            {
                return;
            }

            m_writer.WriteLine("ELSE");
            foreach (var statement in conditionalStatement.Alternative)
            {
                VisitSubExpression(statement.FromLocator(m_domainItemLocator));
            }
        }


        private void VisitSubExpression(IAstNode exp)
        {
            m_writer.Indent++;
            dynamic d = exp;
            this.Visit(d);
            m_writer.Indent--;
        }
    }

    public static class AstExtensions2
    {
        public static T FromLocator<T>(
            this Reference<T> astReference,
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            return domainItemLocator.Get<T>(astReference.Id);
        }
    }
}