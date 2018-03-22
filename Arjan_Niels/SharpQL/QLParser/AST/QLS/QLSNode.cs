using QLParser.AST.QLS.Enums;
using System;
using System.Collections.Generic;
using System.Text;

namespace QLParser.AST.QLS
{
    public class QLSNode : IQLSTraversable
    {
        public string ID { get; private set; }
        public QLSNodeType NodeType { get; private set; }
        public IList<QLSNode> Children { get; private set; }
        public IList<QLSStyle> NodeStyles { get; private set; }

        public QLSNode(QLSNodeType type, string id)
        {
            this.NodeType = type;
            this.ID = id;
            this.Children = new List<QLSNode>();
            this.NodeStyles = new List<QLSStyle>();
        }

        public QLSNode(QLSNodeType type, string id, IList<QLSStyle> styles) : this(type, id)
        {
            this.NodeStyles = styles;
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

            if (this.NodeStyles.Count != 0)
            {
                foreach (var style in this.NodeStyles)
                    builder.Append("\t").Append(style);
                builder.Append("\n");
            }
            return string.Format("{0} {1} {2}", this.NodeType, this.ID, builder);
        }

        public void Accept(IQLSVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}