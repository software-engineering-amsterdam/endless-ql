using QLParser.AST.Nodes;
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
        public static ElementManager ParseChildNode(Node node, ElementManagerController elementManagerController, ElementManagerCollection parent, ExpressionBool condition = null)
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
        private static ElementManagerLeaf CreateElementManager(QuestionNode questionNode, ExpressionBool condition, ElementManager parent, ElementManagerController elementManagerController)
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
                case QValueType.DOUBLE:
                    return new DoubleQuestionManager(node.ID, node.Text, parent, elementManagerController, condition, expression as ExpressionDouble);
            }
            throw new NotImplementedException();
        }


        public static FormManager ApplyQLS(FormManager formManager, QLSNode qLSNode, ElementManagerController controller)
        {
            if (formManager.Identifier != qLSNode.ID)
                throw new InvalidOperationException("Identifiers do not match!");
            List<ElementManagerLeaf> children = formManager.Children.Select(o => (ElementManagerLeaf)o).ToList();
            return ReconstructElementCollection(formManager, ref children, qLSNode.Children, controller) as FormManager;
        }

        private static ElementManagerCollection ReconstructElementCollection(ElementManagerCollection collection, ref List<ElementManagerLeaf> children, IList<QLSNode> qlsChildren, ElementManagerController controller)
        {
            collection.Children.Clear();

            foreach(QLSNode node in qlsChildren)
            {
                switch(node.NodeType)
                {
                    case QLSNodeType.Page:
                    case QLSNodeType.Section:
                        ElementManagerCollection collectionChild = QLSToCollection(node, collection, controller);
                        collectionChild = ReconstructElementCollection(collectionChild, ref children, node.Children, controller);
                        collection.AddChild(collectionChild);
                        break;
                    case QLSNodeType.Question:
                        ElementManagerLeaf child;
                        try {
                            child = children.Where(o => o.Identifier == node.ID).First() as ElementManagerLeaf;
                        } catch
                        {
                            throw new InvalidOperationException(string.Format("Identifier: {0}, used in QLS, not found in QL!", node.ID));
                        }
                        children.Remove(child);
                        collection.AddChild(QLSToLeaf(node, child));
                        break;
                        }
            }
            return collection;
        }

        private static ElementManagerLeaf QLSToLeaf(QLSNode node, ElementManagerLeaf leaf)
        {
            return leaf;
        }

        private static ElementManagerCollection QLSToCollection(QLSNode qlsNode, ElementManager parent, ElementManagerController controller)
        {
            switch(qlsNode.NodeType)
            {
                case QLSNodeType.Page:
                    return new PageManager(qlsNode.ID, qlsNode.ID, parent, controller);
                case QLSNodeType.Section:
                    return new SectionManager(qlsNode.ID, qlsNode.ID, parent, controller);
               
            }
            throw new NotImplementedException();
        }
        /*
        private static ElementManager ApplyQls(ElementManager elementManager, QLSNode qLSNode)
        {
            


            switch (elementManager)
            {
                case ElementManagerCollection collection:

                    break;
                case ElementManagerLeaf leaf:
                    break;
            }
        }*/
    }
}
