using System;
using System.Collections.Generic;

namespace QLParser.AST
{
    public enum QLSNodeType
    {
        Stylesheet,
        Page,
        Section,
        Question
    }

    public class QLSNode
    {
        public string ID { get; set; }
        public QLSNodeType NodeType { get; set; }
        public IList<QLSNode> Children { get; set; }

        public QLSNode(QLSNodeType type, string id)
        {
            this.NodeType = type;
            this.ID = id;
            this.Children = new List<QLSNode>();
        }

        public void AddNode(QLSNode node)
        {
            if (node != null)
                this.Children.Add(node);
            else
                throw new ArgumentNullException("A node can't be null!");
        }
    }
}