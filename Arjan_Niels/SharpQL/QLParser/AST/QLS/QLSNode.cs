using QLParser.AST.QLS.Enums;
using System;
using System.Collections.Generic;
using System.Text;

namespace QLParser.AST.QLS
{
    public abstract class QLSNode : IQLSTraversable
    {
        public string ID { get; private set; }
        public Location Location { get; private set; }
        public QLSNodeType NodeType { get; private set; }
        public IList<QLSNode> Children { get; private set; }
        public IList<QLSStyle> NodeStyles { get; private set; }

        public QLSNode(Location location, QLSNodeType type, string id)
        {
            this.NodeType = type;
            this.Location = location;
            this.ID = id;
            this.Children = new List<QLSNode>();
            this.NodeStyles = new List<QLSStyle>();
        }

        public QLSNode(Location location, QLSNodeType type, string id, IList<QLSStyle> styles) : this(location, type, id)
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
            builder.Append(this.Location);
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

        public abstract void Accept(IQLSVisitor visitor);
    }
}