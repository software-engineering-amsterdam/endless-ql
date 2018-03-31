﻿using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLVisualizer.Controllers;
using QLVisualizer.Expression;
using QLVisualizer.Expression.Types;
using System;
using QLParser.AST.QL.ExpressionNodes.Enums;
using QLVisualizer.Expression.Enums;
using QLVisualizer.Expression.Types.Numeric;

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
                    throw new InvalidOperationException(string.Format("Cannot use expression with type of {0} as a condition.", expression.Type));
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
                    return ParseIdentifyerNode(identifierNode);
                case LiteralNode literalNode:
                    return ParseLiteralNode(literalNode);
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

        private ExpressionValue ParseIdentifyerNode(IdentifierNode identifierNode)
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

        // Absolute values
        private ExpressionValue ParseLiteralNode(LiteralNode literalNode)
        {
            switch (literalNode.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new ExpressionBool(new string[0], () => QValueTypeParser.ParseBoolean(literalNode.Value));
                case QValueType.INTEGER:
                    return new ExpressionInt(new string[0], () => QValueTypeParser.ParseInteger(literalNode.Value));
                case QValueType.DOUBLE:
                    return new ExpressionDouble(new string[0], () => QValueTypeParser.ParseMoney(literalNode.Value));
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
