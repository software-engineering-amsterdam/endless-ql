using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers
{
    public abstract class ElementManagerCollection : ElementManager
    {
        /// <summary>
        /// Children of this ElementManager
        /// </summary>
        public List<ElementManager> Children { get; private set; }

        public ElementManagerCollection(string identifyer, string text, string xmlName, ElementManagerController controller, ExpressionBool activationExpression = null) :
            base(identifyer, text, xmlName, controller, activationExpression)
        {
            Children = new List<ElementManager>();
        }

        /// <summary>
        /// Add child, set parent of ElementManager
        /// </summary>
        /// <param name="elementManager">ElementManager to add as child</param>
        public void AddChild(ElementManager elementManager)
        {
            Children.Add(elementManager);
            elementManager.Parent = this;
        }

        public void AddChildren(IEnumerable<ElementManager> elementManagers)
        {
            foreach (ElementManager elementManager in elementManagers)
                AddChild(elementManager);
        }

        public override void RegisterListeners()
        {
            base.RegisterListeners();
            foreach (ElementManager child in Children)
                child.RegisterListeners();
        }

        public override void ActivationUpdate(ElementManagerLeaf elementManagerLeaf, bool isActive)
        {
            // Only trigger if it contains
            if (_activationExpression != null && _activationExpression.UsedIdentifiers.Contains(elementManagerLeaf.Identifier))
                base.ActivationUpdate(elementManagerLeaf, isActive);


            // Only send to children if parent is active
            if (Active)
                foreach (ElementManagerLeaf manager in Children)
                    manager.ActivationUpdate(elementManagerLeaf, isActive);
        }

        public override IEnumerable<string> GetActivationTargetIDs()
        {
            // Return children and self
            IEnumerable<string> result = Children.SelectMany(o => o.GetActivationTargetIDs());
            return _activationExpression == null ? result : result.Concat(_activationExpression.UsedIdentifiers);
        }

        public override string ToXML()
        {
            return string.Format("<{0} identifier=\"{1}\">{2}</{0}>", XMLElementName, Identifier, string.Join("", Children.Select(o => o.ToXML())));
        }

        public Dictionary<string, ElementManager> FindByID(params string[] identifiers)
        {
            return FindRecursiveByID(new List<string>(identifiers)).Item2;
        }

        public Dictionary<string, ElementManagerLeaf> FindLeafsByID(params string[] identifiers)
        {
            return FindRecursiveLeafsById(new List<string>(identifiers)).Item2;
        }

        private Tuple<List<string>, Dictionary<string, ElementManagerLeaf>> FindRecursiveLeafsById(List<string> targets)
        {
            Dictionary<string, ElementManagerLeaf> result = new Dictionary<string, ElementManagerLeaf>();
            foreach (ElementManager child in Children)
            {
                switch (child)
                {
                    case ElementManagerCollection childCollection:
                        Tuple<List<string>, Dictionary<string, ElementManagerLeaf>> recResult = childCollection.FindRecursiveLeafsById(targets);
                        foreach (string identifier in recResult.Item1)
                            targets.Remove(identifier);

                        result.Concat(recResult.Item2);
                        break;
                    case ElementManagerLeaf childLeaf:
                        if (targets.Contains(childLeaf.Identifier))
                            result.Add(childLeaf.Identifier, childLeaf);
                        break;
                }

                if (targets.Count == 0)
                    break;
            }

            return new Tuple<List<string>, Dictionary<string, ElementManagerLeaf>>(targets, result);
        }

        private Tuple<List<string>, Dictionary<string, ElementManager>> FindRecursiveByID(List<string> targets)
        {
            Dictionary<string, ElementManager> result = new Dictionary<string, ElementManager>();
            foreach (ElementManager child in Children)
            {
                switch (child)
                {
                    case ElementManagerCollection childCollection:
                        Tuple<List<string>, Dictionary<string, ElementManager>> recResult = childCollection.FindRecursiveByID(targets);
                        foreach (string identifier in recResult.Item1)
                            targets.Remove(identifier);

                        result.Concat(recResult.Item2);
                        break;
                    default:
                        if (targets.Contains(child.Identifier))
                            result.Add(child.Identifier, child);
                        break;
                }

                if (targets.Count == 0)
                    break;
            }

            return new Tuple<List<string>, Dictionary<string, ElementManager>>(targets, result);
        }


    }
}
