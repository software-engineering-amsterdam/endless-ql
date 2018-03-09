using QLParser.AST.Nodes;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using QLVisualizer.ElementManagers;
using QLVisualizer.ElementManagers.LeafTypes;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Factories
{
    public static class ElementManagerFactory
    {
        /// <summary>
        /// Parses (AST)Node recursively
        /// </summary>
        /// <param name="node">Node to parse</param>
        /// <param name="condition">Base condition, optional</param>
        /// <returns>Collection of widgets</returns>
        public static IEnumerable<ElementManagerLeaf> CreateWidgets(Node node, ElementManagerController elementManagerController, ExpressionBool condition = null)
        {
            switch (node.Type)
            {
                case NodeType.CONDITIONAL:
                    ExpressionFactory expressionFactory = new ExpressionFactory(elementManagerController);
                    // Parse condition
                    ExpressionBool newCondition = expressionFactory.GetCondition(node as ConditionalNode);

                    // Return children with new condition
                    return node.Children.SelectMany(o => CreateWidgets(o, elementManagerController, (condition == null) ? newCondition : condition.Combine(newCondition, ExpressionOperator.And) as ExpressionBool));

                case NodeType.FORM:
                    // Parse all children
                    return node.Children.SelectMany(o => CreateWidgets(o, elementManagerController, condition));

                case NodeType.QUESTION:
                    // Return widget as array
                    return new ElementManagerLeaf[] { CreateWidget(node as QuestionNode, condition, null, elementManagerController) };

                case NodeType.COMPUTED:
                    return new ElementManagerLeaf[] { CreateComputedWidget(node as ComputedNode, condition, null, elementManagerController) };

                case NodeType.LOGICAL_EXPRESSION:
                    break;

                case NodeType.IDENTIFIER:
                    break;
            }

            // Nothing found, return empty array
            return new ElementManagerLeaf[0];
        }

        /// <summary>
        /// Creates widget from QuestionNode
        /// </summary>
        /// <param name="questionNode">Node to parse</param>
        /// <returns>Parsed widget</returns>
        private static ElementManagerLeaf CreateWidget(QuestionNode questionNode, ExpressionBool condition, ElementManager parent, ElementManagerController elementManagerController)
        {
            switch (questionNode.ValueType)
            {
                case QValueType.BOOLEAN:
                    return new BoolQuestionManager(questionNode.ID, questionNode.Text, parent, elementManagerController, condition);
                case QValueType.INTEGER:
                    return new IntQuestionManager(questionNode.ID, questionNode.Text, parent, elementManagerController, condition);
                case QValueType.TEXT:
                    return new StringQuestionManager(questionNode.ID, questionNode.Text, parent, elementManagerController, condition);
                case QValueType.MONEY:
                    return new MoneyQuestionManager(questionNode.ID, questionNode.Text, parent, elementManagerController, condition);
            }
            throw new InvalidOperationException("Unsupported type: " + questionNode.ValueType);
        }

        /// <summary>
        /// Creates widget with computed value
        /// </summary>
        /// <param name="node">Node</param>
        /// <param name="condition">Condition of widget</param>
        /// <param name="elementManagerController">Widget controller</param>
        /// <returns></returns>
        private static ElementManagerLeaf CreateComputedWidget(ComputedNode node, ExpressionBool condition, ElementManager parent, ElementManagerController elementManagerController)
        {
            ExpressionFactory expressionFactory = new ExpressionFactory(elementManagerController);
            ExpressionValue expression = expressionFactory.ParseExpressionNode(node.Expression);
            switch (node.Expression.GetQValueType())
            {
                case QValueType.BOOLEAN:
                    return new BoolQuestionManager(node.ID, node.Text, parent, elementManagerController, condition, expression as ExpressionBool);
                case QValueType.INTEGER:
                    return new IntQuestionManager(node.ID, node.Text, parent, elementManagerController, condition, expression as ExpressionInt);
                case QValueType.MONEY:
                    return new MoneyQuestionManager(node.ID, node.Text, parent, elementManagerController, condition, expression as ExpressionDouble);
            }
            throw new NotImplementedException();
        }
    }
}
