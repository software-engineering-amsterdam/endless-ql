using QLParser.AST.QL;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;
using System.Linq;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Expression.Enums;
using QLParser.AST.QLS;
using System.Collections.Generic;
using QLParser.AST.QLS.Enums;

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
        /// Parses (AST)Node recursively
        /// </summary>
        /// <param name="node">Node to parse</param>
        /// <param name="condition">Base condition, optional</param>
        /// <returns>Collection of widgets</returns>
        public static ElementManager ParseChildNode(QLNode node, ElementManagerController elementManagerController, ElementManagerCollection parent, ExpressionBool condition = null)
        {
            switch (node.Type)
            {
                case NodeType.CONDITIONAL:
                    ExpressionFactory expressionFactory = new ExpressionFactory(elementManagerController);
                    // Parse condition
                    ExpressionBool newCondition = expressionFactory.GetCondition(node as ConditionalNode);

                    newCondition = (condition == null) ? newCondition : condition.Combine(newCondition, ExpressionOperator.And) as ExpressionBool;

                    // Add children with new condition
                    parent.AddChildren(node.Children.Select(o => ParseChildNode(o, elementManagerController, parent, newCondition)).Where(o => o != null));

                    // return parent
                    return null;

                case NodeType.FORM:
                    throw new InvalidOperationException("Cannot stack form nodes");

                case NodeType.QUESTION:
                    return CreateElementManager(node as QuestionNode, condition, parent, elementManagerController);

                case NodeType.COMPUTED:
                    return CreateComputedWidget(node as ComputedNode, condition, parent, elementManagerController);
            }

            throw new NotImplementedException();
        }

        /// <summary>
        /// Creates widget from QuestionNode
        /// </summary>
        /// <param name="questionNode">Node to parse</param>
        /// <returns>Parsed widget</returns>
        private static ElementManagerLeaf CreateElementManager(QuestionNode questionNode, ExpressionBool condition, ElementManagerCollection parent, ElementManagerController elementManagerController)
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
        private static ElementManagerLeaf CreateComputedWidget(ComputedNode node, ExpressionBool condition, ElementManagerCollection parent, ElementManagerController elementManagerController)
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
                case QValueType.DOUBLE:
                    return new DoubleQuestionManager(node.ID, node.Text, parent, elementManagerController, condition, expression as ExpressionDouble);
            }
            throw new NotImplementedException();
        }

        /// <summary>
        /// Adds QLS to a form
        /// </summary>
        /// <param name="formManager">Target form</param>
        /// <param name="qLSNode">Styling for the form</param>
        /// <param name="controller">Controller for object creation</param>
        /// <returns>Styled form</returns>
        public static FormManager ApplyQLS(FormManager formManager, QLSNode qLSNode, ElementManagerController controller)
        {
            if (formManager.Identifier != qLSNode.ID)
                throw new InvalidOperationException("Identifiers do not match!");
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
            //collection.SetStyles(new List<IQLSElement>(qlsNode.NodeStyles));

            foreach (QLSNode node in qlsNode.Children)
            {
                switch (node.NodeType)
                {
                    case QLSNodeType.Page:
                    case QLSNodeType.Section:
                        ElementManagerCollection collectionChild = QLSToCollection(node, collection, controller);
                        collectionChild = ReconstructElementCollection(collectionChild, ref children, node, controller);
                        collection.AddChild(collectionChild);
                        break;
                    case QLSNodeType.Question:
                        IEnumerable<ElementManagerLeaf> foundMatches = children.Where(o => o.Identifier == node.ID).Select(o => o as ElementManagerLeaf);
                        if(foundMatches.Count() != 1)
                            throw new InvalidOperationException(string.Format("Identifier: {0}, used in QLS, was found {1} times in QL!", node.ID, foundMatches.Count()));

                        ElementManagerLeaf child = foundMatches.First();
                        children.Remove(child);
                        collection.AddChild(QLSToLeaf(node, child));
                        break;
                }
            }
            collection.AddStyle(qlsNode.NodeStyles.ToArray());
            return collection;
        }

        /// <summary>
        /// Sets style of a specific element manager leaf
        /// </summary>
        /// <param name="node">QLS instructions</param>
        /// <param name="leaf">ElementManagerLeaf to apply style to</param>
        /// <returns>Styled element manager leaf</returns>
        private static ElementManagerLeaf QLSToLeaf(QLSNode node, ElementManagerLeaf leaf)
        {
            // Leaf retrieval
            IQLSElement style = new QLSStyle(new QLSWidgetSpecification(WidgetType.DEFAULT, new List<string>()));

            if (node.NodeStyles.Count > 1)
                throw new InvalidOperationException("MULTIPLE STYLES IN LEAF");
            else if (node.NodeStyles.Count == 1)
                style = node.NodeStyles[0];

            leaf.SetStyle(style);
            return leaf;
        }

        /// <summary>
        /// Creates a ElementManagerCollection that is defined in a QLS node
        /// </summary>
        /// <param name="qlsNode">QLS instructions</param>
        /// <param name="parent">Parent object of the new collection</param>
        /// <param name="controller">ElementManagerController for object creation</param>
        /// <returns>ElementManagerCollection as defined by the QLS</returns>
        private static ElementManagerCollection QLSToCollection(QLSNode qlsNode, ElementManagerCollection parent, ElementManagerController controller)
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
