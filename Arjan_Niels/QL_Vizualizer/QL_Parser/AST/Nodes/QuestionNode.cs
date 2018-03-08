namespace QLParser.AST.Nodes
{
    public class QuestionNode : Node
    {
        public string ID { get; private set; }
        public string Text { get; private set; }
        public QValueType ValueType { get; private set; }

        public QuestionNode(string id, string text, QValueType questionType) : base(NodeType.QUESTION)
        {
            this.ID = id;
            this.Text = text;
            this.ValueType = questionType;
        }

        public override string ToString()
        {
            return base.ToString() + string.Format("\tID: {0}\t{1}\t\t{2}", ID, ValueType, Text);
        }
    }
}