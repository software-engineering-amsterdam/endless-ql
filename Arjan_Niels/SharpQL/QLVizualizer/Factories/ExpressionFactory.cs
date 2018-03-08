using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLVisualizer.Controllers;
using QLVisualizer.Expression;
using QLVisualizer.Expression.Types;
using System;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLVisualizer.ElementManagers.LeafTypes;

namespace QLVisualizer.Factories
{
    public class ExpressionFactory
    {
        private ElementManagerController _widgetController;

        public ExpressionFactory(ElementManagerController widgetController)
        {
            _widgetController = widgetController;
        }

        /// <summary>
        /// Transorms ConditionalNode into a boolean Expression
        /// </summary>
        /// <param name="conditionalNode">Node to parse</param>
        /// <returns>Boolean Expression</returns>
        public ExpressionBool GetCondition(ConditionalNode conditionalNode)
        {
            ExpressionValue expression = ParseExpressionNode(conditionalNode.Expression);
            if(expression.Type != typeof(bool))
                throw new InvalidOperationException(string.Format("Cannot use expression with type of {0} as a condition.", expression.Type));
            return expression as ExpressionBool;
        }


        public ExpressionValue ParseExpressionNode(IExpressionNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.ARTHIMETIC_EXPRESSION:
                    return ParseArthimeticNode(node as ArthimetricExpressionNode);
                case NodeType.COMPARISON_EXPRESSION:
                    return ParseComparisonNode(node as ComparisonExpressionNode);
                case NodeType.LOGICAL_EXPRESSION:
                    return ParseLogicalNode(node as LogicalExpressionNode);
                case NodeType.IDENTIFIER:
                    return ParseIdentifyerNode(node as IdentifierNode);
                case NodeType.LITERAL:
                    return ParseLiteralNode(node as LiteralNode);
                default:
                    throw new NotImplementedException();
            }
        }

        // logical expression (&&, ||)
        private ExpressionValue ParseLogicalNode(LogicalExpressionNode logicalExpressionNode)
        {
            ExpressionValue leftExpressionValue = ParseExpressionNode(logicalExpressionNode.Left);
            ExpressionValue rightExpressionValue = ParseExpressionNode(logicalExpressionNode.Right);

            ExpressionOperator expressionOperator = ExpressionOperator.Undefined;
            switch (logicalExpressionNode.Operator)
            {
                case LogicalOperator.AND:
                    expressionOperator = ExpressionOperator.And;
                    break;
                case LogicalOperator.OR:
                    expressionOperator = ExpressionOperator.Or;
                    break;
                default:
                    throw new NotImplementedException();
            }

            return leftExpressionValue.Combine(rightExpressionValue, expressionOperator);

        }

        // compares
        private ExpressionValue ParseComparisonNode(ComparisonExpressionNode comparisonExpressionNode)
        {
            ExpressionValue leftExpressionValue = ParseExpressionNode(comparisonExpressionNode.Left);
            ExpressionValue rightExpressionValue = ParseExpressionNode(comparisonExpressionNode.Right);

            ExpressionOperator expressionOperator = ExpressionOperator.Undefined;
            switch (comparisonExpressionNode.Operator)
            {
                case ComparisonOperator.EQ:
                    expressionOperator = ExpressionOperator.Equals;
                    break;
                case ComparisonOperator.GE:
                    expressionOperator = ExpressionOperator.GreaterEquals;
                    break;
                case ComparisonOperator.GT:
                    expressionOperator = ExpressionOperator.GreaterThan;
                    break;
                case ComparisonOperator.LE:
                    expressionOperator = ExpressionOperator.LessEquals;
                    break;
                case ComparisonOperator.LT:
                    expressionOperator = ExpressionOperator.LessThan;
                    break;
                default:
                    throw new NotImplementedException();
            }

            return leftExpressionValue.Compare(rightExpressionValue, expressionOperator);
        }

        private ExpressionValue ParseArthimeticNode(ArthimetricExpressionNode arthimeticExpressionNode)
        {
            ExpressionValue leftExpressionValue = ParseExpressionNode(arthimeticExpressionNode.Left);
            ExpressionValue rightExpressionValue = ParseExpressionNode(arthimeticExpressionNode.Right);
            ExpressionOperator expressionOperator = ExpressionOperator.Undefined;
            switch (arthimeticExpressionNode.Operator)
            {
                case ArthimetricOperator.DIV:
                    expressionOperator = ExpressionOperator.Divide;
                    break;
                case ArthimetricOperator.MINUS:
                    expressionOperator = ExpressionOperator.Minus;
                    break;
                case ArthimetricOperator.PLUS:
                    expressionOperator = ExpressionOperator.Plus;
                    break;
                case ArthimetricOperator.MULT:
                    expressionOperator = ExpressionOperator.Multiply;
                    break;
                default:
                    throw new NotImplementedException();
            }

            return leftExpressionValue.Combine(rightExpressionValue, expressionOperator);
        }

        // Answer values
        private ExpressionValue ParseIdentifyerNode(IdentifierNode identifierNode)
        {
            switch (identifierNode.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new ExpressionBool(new string[] { identifierNode.ID }, () => { return (_widgetController.GetElementManager(identifierNode.ID) as BoolQuestionManager).Answer.Value; });
                case QValueType.INTEGER:
                    return new ExpressionInt(new string[] { identifierNode.ID }, () => { return (_widgetController.GetElementManager(identifierNode.ID) as IntQuestionManager).Answer.Value; });
                case QValueType.MONEY:
                    return new ExpressionDouble(new string[] { identifierNode.ID }, () => { return (_widgetController.GetElementManager(identifierNode.ID) as MoneyQuestionManager).Answer.Value; });
                default:
                    throw new NotImplementedException();
            }
        }

        // Absolute values
        private ExpressionValue ParseLiteralNode(LiteralNode literalNode)
        {
            switch (literalNode.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new ExpressionBool(new string[0], () => { return bool.Parse(literalNode.Value); });
                case QValueType.INTEGER:
                    return new ExpressionInt(new string[0], () => { return int.Parse(literalNode.Value); });
                case QValueType.MONEY:
                    return new ExpressionDouble(new string[0], () => { return double.Parse(literalNode.Value); });
                default:
                    throw new NotImplementedException();
            }
        }
    }
}
