using QL_Parser.AST.Nodes;
using QL_Vizualizer.Expression;
using QL_Vizualizer.Widgets;
using QL_Vizualizer.Widgets.Types;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Factories
{
    public static class WidgetFactory
    {
        /// <summary>
        /// Parses (AST)Node recursively
        /// </summary>
        /// <param name="node">Node to parse</param>
        /// <param name="condition">Base condition, optional</param>
        /// <returns>Collection of widgets</returns>
        public static IEnumerable<QLWidget> CreateWidgets(Node node, IExpression<bool> condition = null)
        {
            switch (node.Type)
            {
                case NodeType.CONDITIONAL:
                    // Parse condition
                    IExpression<bool> newCondition = ExpressionFactory.GetCondition(node as ConditionalNode);

                    // Return children with new condition
                    return node.Children.SelectMany(o => CreateWidgets(o, (condition == null) ? newCondition : condition.Add(newCondition)));
                
                case NodeType.FORM:
                    // Parse all children
                    return node.Children.SelectMany(o => CreateWidgets(o, condition));

                case NodeType.QUESTION:
                    // Return widget as array
                    return new QLWidget[] { CreateWidget(node as QuestionNode, condition) };

                case NodeType.STATEMENT:
                    break;

                case NodeType.VALUE:
                    break;
            }

            // Nothing found, return empty array
            return new QLWidget[0];
        }

        /// <summary>
        /// Creates widget from QuestionNode
        /// </summary>
        /// <param name="questionNode">Node to parse</param>
        /// <returns>Parsed widget</returns>
        private static QLWidget CreateWidget(QuestionNode questionNode, IExpression<bool> condition)
        {

            switch (questionNode.ValueType)
            {
                case QValueType.BOOLEAN:
                    return new QLWidgetBool(questionNode.ID, questionNode.Question, condition);
                case QValueType.INTEGER:
                    return new QLWidgetInt(questionNode.ID, questionNode.Question, condition);
                case QValueType.TEXT:
                    return new QLWidgetString(questionNode.ID, questionNode.Question, condition);
                case QValueType.MONEY:
                    return new QLWidgetMoney(questionNode.ID, questionNode.Question, condition);
            }
            throw new InvalidOperationException("Unsupported type: " + questionNode.ValueType);
        }
    }
}
