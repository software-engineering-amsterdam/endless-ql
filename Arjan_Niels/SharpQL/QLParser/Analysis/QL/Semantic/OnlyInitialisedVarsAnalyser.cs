using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.Exceptions;

namespace QLParser.Analysis.QL.Semantic
{
    public class OnlyInitialisedVarsAnalyser : IQLAnalyser, IQLVisitor
    {
        private bool isValid = false;

        public bool Analyse(QLNode node)
        {
            this.isValid = true;
            this.Visit(node);

            return isValid;

        }

        private bool IsIdentiierInSymbolTable(IdentifierNode node)
        {
            var valueType = SymbolTable.Get(node.ID);
            if (valueType != QValueType.UNKNOWN)
            {
                return true;
            }
            else
            {
                Analyser.AddMessage(string.Format("Unknown identifier '{0}' in statement", node.ID), MessageType.ERROR);
                return false;
            }
        }

        private bool AnalyseExpression(IExpressionNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.LOGICAL_EXPRESSION:
                case NodeType.COMPARISON_EXPRESSION:
                case NodeType.ARTHIMETRIC_EXPRESSION:
                    var statementNode = (ExpressionNode)node;
                    var leftResult = AnalyseExpression(statementNode.Left);
                    var rightResult = AnalyseExpression(statementNode.Right);
                    return leftResult == rightResult;
                case NodeType.LITERAL:
                    return true;
                case NodeType.IDENTIFIER:
                    var valueNode = (IdentifierNode)node;
                    return IsIdentiierInSymbolTable(valueNode);
                default:
                    throw new UnknownNodeTypeException(string.Format("We don't know what to do with a {0} node.", node.GetNodeType()));
            }
        }

        public void Visit(ComputedNode node)
        {
            if (!AnalyseExpression(node.Expression))
                this.isValid = false;

            VisitChildren(node);
        }

        public void Visit(ConditionalNode node)
        {
            if (!AnalyseExpression(node.Expression))
                this.isValid = false;
            VisitChildren(node);
        }

        public void Visit(QLNode node)
        {
            VisitChildren(node);
        }

        public void Visit(QuestionNode node)
        {
            VisitChildren(node);
        }

        public void Visit(FormNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ExpressionNode node)
        {
            VisitChildren(node);
        }

        private void VisitChildren(QLNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }
    }
}