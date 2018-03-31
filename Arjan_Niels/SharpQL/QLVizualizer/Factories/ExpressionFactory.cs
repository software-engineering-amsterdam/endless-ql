using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;
using QLVisualizer.Controllers;
using QLVisualizer.Expression;
using QLVisualizer.Expression.Enums;
using QLVisualizer.Expression.Types;
using QLVisualizer.Expression.Types.Numeric;
using System;

namespace QLVisualizer.Factories
{
    public class ExpressionFactory
    {
        private ElementManagerController _elementManagerController;

        public ExpressionFactory(ElementManagerController elementManagerController)
        {
            _elementManagerController = elementManagerController;
        }

        /// <summary>
        /// Transorms ConditionalNode into a boolean Expression
        /// </summary>
        /// <param name="conditionalNode">Node to parse</param>
        /// <returns>Boolean Expression</returns>
        public ExpressionBool GetCondition(ConditionalNode conditionalNode)
        {
            ExpressionValue expression = ParseExpressionNode(conditionalNode.Expression);
            switch (expression)
            {
                case ExpressionBool boolExpression:
                    return boolExpression;
                default:
                    throw new InvalidOperationException(string.Format("Cannot use expression with type of {0} as a condition.", expression.ExpressionType));
            }
        }

        public ExpressionValue ParseExpressionNode(IExpressionNode node)
        {
            switch (node)
            {
                case ArthimetricExpressionNode arthimetricNode:
                    return ParseArthimeticNode(arthimetricNode);
                case ComparisonExpressionNode comparisonNode:
                    return ParseComparisonNode(comparisonNode);
                case LogicalExpressionNode logicalNode:
                    return ParseLogicalNode(logicalNode);
                case IdentifierNode identifierNode:
                    return ParseidentifierNode(identifierNode);
                case LiteralNode literalNode:
                    return ParseLiteralNode(literalNode);
                default:
                    throw new NotImplementedException();
            }
        }

        private ExpressionValue ParseLogicalNode(LogicalExpressionNode logicalExpressionNode)
        {
            ExpressionValue leftExpressionValue = ParseExpressionNode(logicalExpressionNode.Left);
            ExpressionValue rightExpressionValue = ParseExpressionNode(logicalExpressionNode.Right);

            ExpressionOperator expressionOperator = ExpressionOperator.Undefined;
            switch (logicalExpressionNode.Operator)
            {
                case LogicalOperator.And:
                    expressionOperator = ExpressionOperator.And;
                    break;
                case LogicalOperator.Or:
                    expressionOperator = ExpressionOperator.Or;
                    break;
                default:
                    throw new NotImplementedException();
            }

            return leftExpressionValue.Combine(rightExpressionValue, expressionOperator);

        }


        private ExpressionValue ParseComparisonNode(ComparisonExpressionNode comparisonExpressionNode)
        {
            ExpressionValue leftExpressionValue = ParseExpressionNode(comparisonExpressionNode.Left);
            ExpressionValue rightExpressionValue = ParseExpressionNode(comparisonExpressionNode.Right);

            ExpressionOperator expressionOperator = ExpressionOperator.Undefined;
            switch (comparisonExpressionNode.Operator)
            {
                case ComparisonOperator.Equal:
                    expressionOperator = ExpressionOperator.Equals;
                    break;
                case ComparisonOperator.GreaterEqual:
                    expressionOperator = ExpressionOperator.GreaterEquals;
                    break;
                case ComparisonOperator.GreaterThan:
                    expressionOperator = ExpressionOperator.GreaterThan;
                    break;
                case ComparisonOperator.LessEqual:
                    expressionOperator = ExpressionOperator.LessEquals;
                    break;
                case ComparisonOperator.LessThan:
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
                case ArthimetricOperator.Div:
                    expressionOperator = ExpressionOperator.Divide;
                    break;
                case ArthimetricOperator.Minus:
                    expressionOperator = ExpressionOperator.Minus;
                    break;
                case ArthimetricOperator.Plus:
                    expressionOperator = ExpressionOperator.Plus;
                    break;
                case ArthimetricOperator.Mult:
                    expressionOperator = ExpressionOperator.Multiply;
                    break;
                default:
                    throw new NotImplementedException();
            }

            return leftExpressionValue.Combine(rightExpressionValue, expressionOperator);
        }

        private ExpressionValue ParseidentifierNode(IdentifierNode identifierNode)
        {
            switch (identifierNode.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new ExpressionBool(new LazyElementExpressionLink<bool>(_elementManagerController, identifierNode.ID));
                case QValueType.INTEGER:
                    return new ExpressionInt(new LazyElementExpressionLink<int>(_elementManagerController, identifierNode.ID));
                case QValueType.DOUBLE:
                case QValueType.MONEY:
                    return new ExpressionDouble(new LazyElementExpressionLink<double>(_elementManagerController, identifierNode.ID));
                case QValueType.TEXT:
                    return new ExpressionText(new LazyElementExpressionLink<string>(_elementManagerController, identifierNode.ID));
                case QValueType.HEX:
                    return new ExpressionHex(new LazyElementExpressionLink<Hexadecimal>(_elementManagerController, identifierNode.ID));
                default:
                    throw new NotImplementedException();
            }
        }

        private ExpressionValue ParseLiteralNode(LiteralNode literalNode)
        {
            switch (literalNode.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new ExpressionBool(new string[0], () => QValueTypeParser.ParseBoolean(literalNode.Value));
                case QValueType.INTEGER:
                    return new ExpressionInt(new string[0], () => QValueTypeParser.ParseInteger(literalNode.Value));
                case QValueType.DOUBLE:
                case QValueType.MONEY:
                    return new ExpressionDouble(new string[0], () => QValueTypeParser.ParseDouble(literalNode.Value));
                case QValueType.TEXT:
                    return new ExpressionText(new string[0], () => QValueTypeParser.ParseText(literalNode.Value));
                case QValueType.HEX:
                    return new ExpressionHex(new string[0], () => QValueTypeParser.ParseHexadecimal(literalNode.Value));
                default:
                    throw new NotImplementedException();
            }
        }
    }
}
