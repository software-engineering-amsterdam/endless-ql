using QLParser.Analysis.QL.Evaluator;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.Analysis.QL.Semantic
{
    /// <summary>
    /// This analyser checks if all the statements are valid and of the right type.
    /// </summary>
    public class StatementTypeAnalyser : IQLAnalyser
    {
        public bool Analyse(QLNode node)
        {
            var result = true;
            var expression = GetExpression(node);
            if (expression != null && AnalyseExpression(expression) == StatementType.Unknown)
            {
                Analyser.AddMessage(string.Format("{0} - This expression isn't valid.", node.Location), LanguageType.QL, MessageType.ERROR);
                return false;
            }

            switch (node)
            {
                case QLCollectionNode collectionNode:
                    foreach (QLNode child in collectionNode.Children)
                        if (!Analyse(child))
                            result = false;
                    break;
                default:
                    return true;
            }

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

                case NodeType.TEXT_CONCATINATION:
                    var textNode = (TextConcatinationNode)node;
                    return Analyze(textNode);

                case NodeType.LITERAL:
                case NodeType.IDENTIFIER:
                    return StatementTypeEvaluator.GetStatementType(node);
            }

            throw new UnknownNodeTypeException(string.Format("We don't know what to do with this node: {0}", node.GetNodeType()));
        }

        /// <summary>
        /// This function gets the expresion from several type of nodes.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        private IExpressionNode GetExpression(QLNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.CONDITIONAL:
                    var conditional = (ConditionalNode)node;
                    return conditional.Expression;
                case NodeType.COMPUTED:
                    var computed = (ComputedNode)node;
                    return computed.Expression;
                case NodeType.ARTHIMETRIC_EXPRESSION:
                case NodeType.COMPARISON_EXPRESSION:
                case NodeType.LOGICAL_EXPRESSION:
                    return node as IExpressionNode;
                default:
                    return null;
            }
        }

        private StatementType Analyze(TextConcatinationNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            return left == right && left == StatementType.Text ? StatementType.Text : StatementType.Unknown;
        }

        private StatementType Analyze(ComparisonExpressionNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            switch (node.Operator)
            {
                case ComparisonOperator.EQ:
                    return (left == right) ? StatementType.Boolean : StatementType.Unknown;
                default:
                    return (left == right && left == StatementType.Numeric) ? StatementType.Boolean : StatementType.Unknown;
            }
        }

        private StatementType Analyze(ArthimetricExpressionNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            return (left == right && left == StatementType.Numeric) ? StatementType.Numeric : StatementType.Unknown;
        }

        private StatementType Analyze(LogicalExpressionNode node)
        {
            StatementType left = AnalyseExpression(node.Left);
            StatementType right = AnalyseExpression(node.Right);

            return (left == right && left == StatementType.Boolean) ? StatementType.Boolean : StatementType.Unknown;
        }
    }
}