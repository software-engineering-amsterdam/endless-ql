using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLParser.AST.QLS.Enums;
using QLVisualizer.Controllers;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Expression.Enums;
using QLVisualizer.Expression.Types;
using QLVisualizer.Expression.Types.Numeric;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Factories
{
    public static class ElementManagerFactory
    {

        public static FormManager CreateForm(FormNode node, ElementManagerController elementManagerController)
        {
            FormManager form = new FormManager(node.FormName, node.FormName, elementManagerController);

            // Add all children
            form.AddChildren(node.Children.Select(o => ParseChildNode(o, elementManagerController, form)).Where(o => o != null));

            return form;
        }

        /// <summary>
        /// Parses Node recursively
        /// </summary>
        /// <param name="node">Node to parse</param>
        /// <param name="condition">Base condition, optional</param>
        /// <returns>Collection of widgets</returns>
        public static ElementManager ParseChildNode(QLNode node, ElementManagerController elementManagerController, ElementManagerCollection parent, ExpressionBool condition = null)
        {
            ExpressionFactory expressionFactory = new ExpressionFactory(elementManagerController);
            switch (node)
            {
                case ConditionalNode conditionalNode:
                    // Parse condition
                    ExpressionBool newCondition = expressionFactory.GetCondition(conditionalNode);

                    newCondition = (condition == null) ? newCondition : condition.Combine(newCondition, ExpressionOperator.And) as ExpressionBool;

                    // Add children with new condition
                    parent.AddChildren(conditionalNode.Children.Select(o => ParseChildNode(o, elementManagerController, parent, newCondition)).Where(o => o != null));
                    return null;

                case QuestionNode questionNode:
                    return CreateElementManager(questionNode, condition, parent, elementManagerController, null);

                case ComputedNode computedNode:
                    ExpressionValue expression = expressionFactory.ParseExpressionNode(computedNode.Expression);
                    return CreateElementManager(computedNode, condition, parent, elementManagerController, expression);
            }

            throw new NotImplementedException();
        }

        private static ElementManagerLeaf CreateElementManager(IQuestionable identifiedNode, ExpressionBool condition, ElementManagerCollection parent, ElementManagerController elementManagerController, ExpressionValue activationExpression)
        {
            switch (identifiedNode.ValueType)
            {
                case QValueType.BOOLEAN:
                    return new BoolQuestionManager(identifiedNode.ID, identifiedNode.Text, parent, elementManagerController, condition, activationExpression as ExpressionBool);
                case QValueType.INTEGER:
                    return new IntQuestionManager(identifiedNode.ID, identifiedNode.Text, parent, elementManagerController, condition, activationExpression as ExpressionInt);
                case QValueType.TEXT:
                    return new StringQuestionManager(identifiedNode.ID, identifiedNode.Text, parent, elementManagerController, condition, activationExpression as ExpressionText);
                case QValueType.MONEY:
                    return new MoneyQuestionManager(identifiedNode.ID, identifiedNode.Text, parent, elementManagerController, condition, activationExpression as ExpressionDouble);
                case QValueType.HEX:
                    return new HexQuestionManager(identifiedNode.ID, identifiedNode.Text, parent, elementManagerController, condition, activationExpression as ExpressionHex);
            }
            throw new InvalidOperationException("Unsupported type: " + identifiedNode.ValueType);
        }

        /// <summary>
        /// Adds QLS to a form
        /// </summary>
        /// <param name="formManager">Target form</param>
        /// <param name="qLSNode">Styling for the form</param>
        /// <param name="controller">Controller for object creation</param>
        /// <returns>Styled form</returns>
        public static FormManager ApplyQLS(FormManager formManager, QLSNode qLSNode, ElementManagerController controller, ref List<string> errors)
        {
            if (formManager.Identifier != qLSNode.ID)
            {
                errors.Add("Identifiers of ql and qls do not match!");
                return formManager;
            }

            List<ElementManagerLeaf> children = formManager.Children.Select(o => (ElementManagerLeaf)o).ToList();

            return ReconstructElementCollection(formManager, ref children, qLSNode, controller) as FormManager;
        }

        /// <summary>
        /// Creates the elementManager style as provided by the QLS node by inserting
        /// the manager objects provided by the QLS and insterts the given children into 
        /// the tree on the positions defined in the QLS
        /// </summary>
        /// <param name="collection">Collection element to be reconstructed</param>
        /// <param name="children">Children that can occur as a child of the collection element</param>
        /// <param name="qlsChildren">Children of the QLS node that mached the collection node</param>
        /// <param name="controller">ElementManagerController for element creation</param>
        /// <returns>Element manager collection that contains all QLS defined children</returns>
        private static ElementManagerCollection ReconstructElementCollection(ElementManagerCollection collection, ref List<ElementManagerLeaf> children, QLSNode qlsNode, ElementManagerController controller)
        {
            collection.Children.Clear();

            foreach (QLSNode node in qlsNode.Children)
            {
                switch (node.NodeType)
                {
                    case QLSNodeType.Page:
                    case QLSNodeType.Section:
                        ElementManagerCollection collectionChild = AddQLSToCollection(node, collection, controller);
                        collectionChild = ReconstructElementCollection(collectionChild, ref children, node, controller);
                        collection.AddChild(collectionChild);
                        break;
                    case QLSNodeType.Question:
                        IEnumerable<ElementManagerLeaf> foundMatches = children.Where(o => o.Identifier == node.ID).Select(o => o as ElementManagerLeaf);
                        if (foundMatches.Count() != 1)
                            throw new InvalidOperationException(string.Format("Identifier: {0}, used in QLS, was found {1} times in QL!", node.ID, foundMatches.Count()));

                        ElementManagerLeaf child = foundMatches.First();
                        children.Remove(child);
                        collection.AddChild(AddQLSToLeaf(node, child));
                        break;
                }
            }
            collection.AddStyle(qlsNode.NodeStyles.ToArray());
            return collection;
        }

        private static ElementManagerLeaf AddQLSToLeaf(QLSNode node, ElementManagerLeaf leaf)
        {
            QLSStyle style = new QLSStyle(QValueType.UNKNOWN, new QLSWidgetSpecification(WidgetType.DEFAULT, new List<string>()));

            if (node.NodeStyles.Count > 1)
                throw new InvalidOperationException("Multiple styles in leaf node");
            else if (node.NodeStyles.Count == 1)
                style = node.NodeStyles[0];

            leaf.SetStyle(style);
            return leaf;
        }

        private static ElementManagerCollection AddQLSToCollection(QLSNode qlsNode, ElementManagerCollection parent, ElementManagerController controller)
        {
            switch (qlsNode.NodeType)
            {
                case QLSNodeType.Page:
                    return new PageManager(qlsNode.ID, qlsNode.ID, parent, controller);
                case QLSNodeType.Section:
                    return new SectionManager(qlsNode.ID, qlsNode.ID, parent, controller);
            }
            throw new NotImplementedException();
        }
    }
}
