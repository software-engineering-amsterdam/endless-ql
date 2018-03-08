using QL_Parser.AST.Nodes;
using QL_Vizualizer.Controllers;
using QL_Vizualizer.Expression;
using QL_Vizualizer.Expression.Types;
using QL_Vizualizer.ElementManagers;
using QL_Vizualizer.ElementManagers.Types;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Factories
{
    public static class ElementManagerFactory
    {
        /// <summary>
        /// Parses (AST)Node recursively
        /// </summary>
        /// <param name="node">Node to parse</param>
        /// <param name="condition">Base condition, optional</param>
        /// <returns>Collection of widgets</returns>
        public static IEnumerable<ElementManager> CreateWidgets(Node node, ElementManagerController widgetController, ExpressionBool condition = null)
        {
            switch (node.Type)
            {
                case NodeType.CONDITIONAL:
                    ExpressionFactory expressionFactory = new ExpressionFactory(widgetController);
                    // Parse condition
                    ExpressionBool newCondition = expressionFactory.GetCondition(node as ConditionalNode);

                    // Return children with new condition
                    return node.Children.SelectMany(o => CreateWidgets(o, widgetController, (condition == null) ? newCondition : condition.Combine(newCondition, ExpressionOperator.And) as ExpressionBool));

                case NodeType.FORM:
                    // Parse all children
                    return node.Children.SelectMany(o => CreateWidgets(o, widgetController, condition));

                case NodeType.QUESTION:
                    // Return widget as array
                    return new ElementManager[] { CreateWidget(node as QuestionNode, condition) };

                case NodeType.COMPUTED:
                    return new ElementManager[] { CreateComputedWidget(node as ComputedNode, condition, widgetController) };

                case NodeType.LOGICAL_EXPRESSION:
                    break;

                case NodeType.IDENTIFIER:
                    break;
            }

            // Nothing found, return empty array
            return new ElementManager[0];
        }

        /// <summary>
        /// Creates widget from QuestionNode
        /// </summary>
        /// <param name="questionNode">Node to parse</param>
        /// <returns>Parsed widget</returns>
        private static ElementManager CreateWidget(QuestionNode questionNode, ExpressionBool condition)
        {
            switch (questionNode.ValueType)
            {
                case QValueType.BOOLEAN:
                    return new BoolElementManager(questionNode.ID, questionNode.Text, condition);
                case QValueType.INTEGER:
                    return new IntElementManager(questionNode.ID, questionNode.Text, condition);
                case QValueType.TEXT:
                    return new StringElementManager(questionNode.ID, questionNode.Text, condition);
                case QValueType.MONEY:
                    return new MoneyElementManager(questionNode.ID, questionNode.Text, condition);
            }
            throw new InvalidOperationException("Unsupported type: " + questionNode.ValueType);
        }

        /// <summary>
        /// Creates widget with computed value
        /// </summary>
        /// <param name="node">Node</param>
        /// <param name="condition">Condition of widget</param>
        /// <param name="widgetController">Widget controller</param>
        /// <returns></returns>
        private static ElementManager CreateComputedWidget(ComputedNode node, ExpressionBool condition, ElementManagerController widgetController)
        {
            ExpressionFactory expressionFactory = new ExpressionFactory(widgetController);
            ExpressionValue expression = expressionFactory.ParseExpressionNode(node.Expression);
            switch (node.Expression.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new BoolElementManager(node.ID, node.Text, condition, expression as ExpressionBool);
                case QValueType.INTEGER:
                    return new IntElementManager(node.ID, node.Text, condition, expression as ExpressionInt);
                case QValueType.MONEY:
                    return new MoneyElementManager(node.ID, node.Text, condition, expression as ExpressionDouble);
            }
            throw new NotImplementedException();
        }
    }
}
