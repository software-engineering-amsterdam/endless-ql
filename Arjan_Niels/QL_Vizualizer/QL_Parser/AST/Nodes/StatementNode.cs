namespace QL_Parser.AST.Nodes
{
    public class StatementNode : Node, IExpressionNode
    {
        public string ID { get; private set; }
        public StatementNode LeftSide { get; private set; }
        public string Operator { get; private set; }
        public StatementNode RightSide { get; private set; }

        public StatementNode(string id) : base(NodeType.VALUE)
        {
            this.ID = id;
        }

        public StatementNode(StatementNode lhs, string opr, StatementNode rhs) : base(NodeType.STATEMENT)
        {
            this.LeftSide = lhs;
            this.Operator = opr;
            this.RightSide = rhs;
        }

        public override string ToString()
        {
            if (Operator != null && RightSide != null)
                return string.Format("{0} ({1} {2} {3})", base.ToString(), LeftSide, Operator, RightSide);
            else
                return string.Format(" {0} {1}", base.ToString(), ID);
        }

        public object GetValue()
        {
            throw new System.NotImplementedException();
        }
    }
}
