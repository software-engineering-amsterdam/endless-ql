namespace QL_Parser.AST.Nodes
{
    public class ConditionalNode : Node
    {
        public StatementNode StatementNode { get; private set; }

        public ConditionalNode(StatementNode statementNode) : base(NodeType.CONDITIONAL)
        {
            this.StatementNode = statementNode;
        }

        public override string ToString()
        {
            return string.Format("{0} {1}", base.ToString(), StatementNode);
        }
    }
}
