using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.Exceptions;

namespace QLParser.Analysis.QL.Semantic
{
    /// <summary>
    /// This analyser will check if all the variables in the form are initialized by a question.
    /// </summary>
    public class OnlyInitialisedVarsAnalyser : IQLAnalyser, IQLVisitor
    {
        private bool _isValid = false;

        public bool Analyse(QLNode node)
        {
            this._isValid = true;
            this.Visit((dynamic)node);

            return this._isValid;
        }

        private bool IsIdentiierInSymbolTable(IdentifierNode node)
        {
            var valueType = SymbolTable.Get(node.ID);
            if (valueType != QValueType.Unknown)
            {
                return true;
            }
            else
            {
                Analyser.AddMessage(string.Format("{0} Unknown identifier '{1}' in statement", node.Location, node.ID), Language.QL, MessageType.ERROR);
                return false;
            }
        }

        private bool AnalyseExpression(IExpressionNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.LogicalExpression:
                case NodeType.ComparisonExpression:
                case NodeType.ArthimetricExpression:
                case NodeType.TextConcatination:
                    var statementNode = (ExpressionNode)node;
                    var leftResult = AnalyseExpression(statementNode.Left);
                    var rightResult = AnalyseExpression(statementNode.Right);
                    return leftResult == rightResult;
                case NodeType.Literal:
                    return true;
                case NodeType.Identifier:
                    var valueNode = (IdentifierNode)node;
                    return IsIdentiierInSymbolTable(valueNode);
                default:
                    throw new UnknownNodeTypeException(string.Format("We don't know what to do with a {0} node.", node.GetNodeType()));
            }
        }

        public void Visit(ComputedNode node)
        {
            if (!AnalyseExpression(node.Expression))
                this._isValid = false;
        }

        public void Visit(ConditionalNode node)
        {
            if (!AnalyseExpression(node.Expression))
                this._isValid = false;
            VisitChildren(node);
        }

        public void Visit(QLNode node)
        {
            return;
        }

        public void Visit(QuestionNode node)
        {
            return;
        }

        public void Visit(FormNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ExpressionNode node)
        {
            return;
        }

        private void VisitChildren(QLCollectionNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }

        public void Visit(QLCollectionNode node)
        {
            VisitChildren(node);
        }
    }
}