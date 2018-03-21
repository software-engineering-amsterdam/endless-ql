using QLParser.AST.QLS.Enums;
using System;
using System.Collections.Generic;
using System.Text;

namespace QLParser.AST.QLS
{
    public class QLSNode
    {
        public string ID { get; private set; }
        public QLSNodeType NodeType { get; private set; }
        public IList<QLSNode> Children { get; private set; }
        public QLSStyle NodeStyle { get; private set; }

        public QLSNode(QLSNodeType type, string id)
        {
            this.NodeType = type;
            this.ID = id;
            this.Children = new List<QLSNode>();
        }

        public QLSNode(QLSNodeType type, string id, QLSStyle style) : this(type, id)
        {
            this.NodeStyle = style;
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

            if (this.NodeStyle != null)
            {
                builder.Append("\t").Append(this.NodeStyle);
                builder.Append("\n");
            }
            return string.Format("{0} {1} {2}", this.NodeType, this.ID, builder);
        }
    }
}