using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.Analysis.Semantic
{
    public class BooleanStatementnalyser : IAnalyser
    {
        private enum StatementType
        {
            BOOLEAN,
            TEXT,
            NUMERIC,
            UNKNOWN
        }

        public bool Analyse(Node node)
        {
            var result = true;
            var expression = GetExpression(node);
            if (expression != null && AnalyseExpression(expression) == StatementType.UNKNOWN)
            {
                Analyser.AddMessage("This expression isn't valid.", MessageType.ERROR);
                return false;
            }

            foreach (Node child in node.Children)
                if (!Analyse(child))
                    result = false;

            return result;
        }

        private StatementType AnalyseExpression(IExpressionNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.LOGICAL_EXPRESSION:
                    var logicalNode = (LogicalExpressionNode)node;
                    return Analyze(logicalNode);

                case NodeType.COMPARISON_EXPRESSION:
                    var comparisonNode = (ComparisonExpressionNode)node;
                    return Analyze(comparisonNode);

                case NodeType.ARTHIMETRIC_EXPRESSION:
                    var arthimetric = (ArthimetricExpressionNode)node;
                    return Analyze(arthimetric);

                case NodeType.LITERAL:
                case NodeType.IDENTIFIER:
                    return GetStatementType(node);
            }

            throw new UnknownNodeTypeException(string.Format("We don't know what to do with this node: {0}", node.GetNodeType()));
        }

        /// <summary>
        /// This function gets the expresion from several type of nodes.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        private IExpressionNode GetExpression(Node node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.CONDITIONAL:
                    var conditional = (ConditionalNode)node;
                    return conditional.Expression;
                case NodeType.COMPUTED:
                    var computed = (ComputedNode)node;
                    return computed.Expression;
                default:
                    return null;
            }
        }

        /// <summary>
        /// Translate QValueType to StatementType.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        private StatementType GetStatementType(IExpressionNode node)
        {
            switch (node.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return StatementType.BOOLEAN;
                case QValueType.TEXT:
                    return StatementType.TEXT;
                case QValueType.MONEY:
                case QValueType.INTEGER:
                case QValueType.DOUBLE:
                    return StatementType.NUMERIC;

                case QValueType.UNKNOWN:
                    return StatementType.UNKNOWN;
            }

            throw new UnknownQValueTypeException("We don't know what to do with this QValueType.");
        }

        private StatementType Analyze(ComparisonExpressionNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            switch (node.Operator)
            {
                case ComparisonOperator.EQ:
                    return (left == right) ? StatementType.BOOLEAN : StatementType.UNKNOWN;
                default:
                    return (left == right && left == StatementType.NUMERIC) ? StatementType.BOOLEAN : StatementType.UNKNOWN;
            }
        }

        private StatementType Analyze(ArthimetricExpressionNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            return (left == right && left == StatementType.NUMERIC) ? StatementType.NUMERIC : StatementType.UNKNOWN;
        }

        private StatementType Analyze(LogicalExpressionNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            return (left == right && left == StatementType.BOOLEAN) ? StatementType.BOOLEAN : StatementType.UNKNOWN;
        }
    }
}