using System;
using System.Collections.Generic;

namespace QL_Parser.AST.Nodes
{
    public abstract class Node
    {
        public NodeType Type { get; private set; }
        public List<Node> Children { get; private set; }

        public Node(NodeType type)
        {
            this.Type = type;
            this.Children = new List<Node>();
        }

        public void AddNode(Node node)
        {
            if (node == null)
                throw new ArgumentNullException("A node can't be null");
            else
                this.Children.Add(node);
        }

        public override string ToString()
        {
            return string.Format("{0}", Type);
        }

        public NodeType GetNodeType()
        {
            return this.Type;
        }
    }
}