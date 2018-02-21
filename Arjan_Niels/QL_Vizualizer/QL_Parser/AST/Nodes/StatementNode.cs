namespace QL_Parser.AST.Nodes
{
    public class StatementNode : Node
    {
        public string ID { get; private set; }
        public StatementNode lhs { get; private set; }
        public string opr { get; private set; }
        public StatementNode rhs { get; private set; }

        public StatementNode(string id) : base(NodeType.STATEMENT)
        {
            this.ID = id;
        }

        public StatementNode(StatementNode lhs, string opr, StatementNode rhs) : base(NodeType.STATEMENT)
        {
            this.lhs = lhs;
            this.opr = opr;
            this.rhs = rhs;
        }

        public override string ToString()
        {
            if (opr != null && rhs != null)
                return string.Format("{0} ({1} {2} {3})", base.ToString(), lhs, opr, rhs);
            else
                return string.Format(" {0} {1}", base.ToString(), ID);
        }
    }
}
