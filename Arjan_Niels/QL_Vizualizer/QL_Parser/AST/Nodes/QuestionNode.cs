namespace QL_Parser.AST.Nodes
{
    public class QuestionNode : Node
    {
        public string ID { get; private set; }
        public string Question { get; private set; }
        public QValueType ValueType { get; private set; }

        public QuestionNode(string id, string question, QValueType questionType) : base(NodeType.QUESTION)
        {
            this.ID = id;
            this.Question = question;
            this.ValueType = questionType;
        }

        public override string ToString()
        {
            return base.ToString() + string.Format("\tID: {0}\t{1}\t\t{2}", ID, ValueType, Question);
        }
    }
}
