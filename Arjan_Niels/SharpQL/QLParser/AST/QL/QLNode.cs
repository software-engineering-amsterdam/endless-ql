using System;
using System.Collections.Generic;

namespace QLParser.AST.QL
{
    public abstract class QLNode
    {
        public NodeType Type { get; private set; }
        public List<QLNode> Children { get; private set; }
        public Location Location { get; private set; }

        public QLNode(Location location, NodeType type)
        {
            this.Type = type;
            this.Children = new List<QLNode>();
            this.Location = location;
        }

        public void AddNode(QLNode node)
        {
            if (node == null)
                throw new ArgumentNullException("A node can't be null");
            else
                this.Children.Add(node);
        }

        public override string ToString()
        {
            return string.Format("{0}{1}", this.Location, this.Type);
        }

        public NodeType GetNodeType()
        {
            return this.Type;
        }
    }
}