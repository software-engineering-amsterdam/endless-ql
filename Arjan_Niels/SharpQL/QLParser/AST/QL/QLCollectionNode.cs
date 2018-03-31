using System;
using System.Collections.Generic;

namespace QLParser.AST.QL
{
    public abstract class QLCollectionNode : QLNode
    {
        public IList<QLNode> Children { get; private set; }

        public QLCollectionNode(Location location, NodeType type) : base(location, type)
        {
            this.Children = new List<QLNode>();
        }

        public void AddNode(QLNode node)
        {
            if (node == null)
                throw new ArgumentNullException("A node can't be null");
            else
                this.Children.Add(node);
        }
    }
}
