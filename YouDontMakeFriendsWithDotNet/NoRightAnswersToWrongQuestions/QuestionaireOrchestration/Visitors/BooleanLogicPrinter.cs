using System.CodeDom.Compiler;
using System.IO;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;

namespace QuestionaireOrchestration.Visitors
{
    public class BooleanLogicPrinter : 
        IBooleanLogicPrinter,
        IAstVisitor,
        IAstVisitor<IBooleanVariableNode>,
        IAstVisitor<IAndNode>,
        IAstVisitor<IOrNode>,
        IAstVisitor<INegateNode>,
        IAstVisitor<IBooleanLiteralNode>
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private IndentedTextWriter m_writer;

        public TextWriter Writer
        {
            get { return m_writer; }
            set { m_writer = new IndentedTextWriter(value); }
        }

        public BooleanLogicPrinter(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public void Print(IBooleanLogicNode node)
        {
            dynamic d = node;
            m_writer.Write("Statement: ");
            VisitSubExpression(d);
        }

        public void Visit(IBooleanVariableNode andNode)
        {
            m_writer.WriteLine(andNode.VariableName);
        }
        
        private void VisitSubExpression(IBooleanLogicNode exp)
        {
            m_writer.Indent++;
            dynamic d = exp;
            this.Visit(d);
            m_writer.Indent--;
        }

        public void Visit(IAndNode andNode)
        {
            m_writer.WriteLine("AND");
            m_writer.Write("|->");
            var lhs = andNode
                .LeftExpression
                .ToDomainItem(m_domainItemLocator);
            VisitSubExpression(lhs);

            m_writer.Write("|->");
            var rhs = andNode
                .RightExpression
                .ToDomainItem(m_domainItemLocator);
            VisitSubExpression(rhs);
        }

        public void Visit(IOrNode orNode)
        {
            m_writer.WriteLine("OR");
            m_writer.Write("|->");
            var lhs = orNode
                .LeftExpression
                .ToDomainItem(m_domainItemLocator);
            VisitSubExpression(lhs);

            m_writer.Write("|->");
            var rhs = orNode
                .RightExpression
                .ToDomainItem(m_domainItemLocator);
            VisitSubExpression(rhs);
        }

        public void Visit(INegateNode negateNode)
        {
            m_writer.WriteLine("NEG");
            m_writer.Write("|->");
            var child = negateNode
                .Expression
                .ToDomainItem(m_domainItemLocator);
            VisitSubExpression(child);
        }

        public void Visit(IBooleanLiteralNode booleanLiteralNode)
        {
            m_writer.WriteLine(booleanLiteralNode.Value);
        }
    }
}