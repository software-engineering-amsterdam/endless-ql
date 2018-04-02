namespace QLParser.AST.QL
{
    public abstract class QLNode : IQLTraversable
    {
        public NodeType Type { get; private set; }
        public Location Location { get; private set; }

        public QLNode(Location location, NodeType type)
        {
            this.Type = type;
            this.Location = location;
        }

        public override string ToString()
        {
            return string.Format("{0}{1}", this.Location, this.Type);
        }

        public NodeType GetNodeType()
        {
            return this.Type;
        }

        public abstract void Accept(IQLVisitor visitor);
    }
}