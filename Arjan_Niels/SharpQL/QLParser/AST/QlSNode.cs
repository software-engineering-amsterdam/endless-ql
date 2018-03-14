using System;
using System.Collections.Generic;
using System.Text;

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

        public override string ToString()
        {
            StringBuilder builder = new StringBuilder();
            builder.Append("\n");
            foreach (QLSNode child in this.Children)
                builder.Append(child.ToString());

            return string.Format("{0} {1} {2}", this.NodeType, this.ID, builder.ToString());
        }
    }
}