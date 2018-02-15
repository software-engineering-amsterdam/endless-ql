namespace QL_Parser.AST.Nodes
{
    public class QuestionNode : Node
    {
        public string ID { get; private set; }
        public string Question { get; private set; }

        public QuestionNode(NodeType type, string id, string question) : base(type)
        {
            this.ID = id;
            this.Question = question;
        }
    }
}
