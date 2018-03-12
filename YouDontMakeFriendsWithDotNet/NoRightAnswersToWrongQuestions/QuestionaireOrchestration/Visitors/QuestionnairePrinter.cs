using System.CodeDom.Compiler;
using System.IO;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

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

        public void Print(IQuestionnaireNode node)
        {
            dynamic d = node;
            this.Visit(d);
        }

        private void Visit(IQuestionnaireRootNode node)
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
            m_writer.WriteLine($"IF  ({conditionalStatement.Definition})");
            m_writer.WriteLine("THEN");
            foreach (var statement in conditionalStatement.Consequent)
            {
                VisitSubExpression(statement.ToDomainItem(m_domainItemLocator));
            }

            if (conditionalStatement.Alternative == null)
            {
                return;
            }

            m_writer.WriteLine("ELSE");
            foreach (var statement in conditionalStatement.Alternative)
            {
                VisitSubExpression(statement.ToDomainItem(m_domainItemLocator));
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
}