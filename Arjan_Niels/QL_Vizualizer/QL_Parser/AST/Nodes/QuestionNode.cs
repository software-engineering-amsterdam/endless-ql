namespace QL_Parser.AST.Nodes
{
    public enum QuestionType
    {
        BOOLEAN,
        MONEY,
        TEXT,
        INTEGER
    }

    public class QuestionNode : Node
    {
        public string ID { get; private set; }
        public string Question { get; private set; }
        public QuestionType QuestionType { get; private set; }

        public QuestionNode(string id, string question, QuestionType questionType) : base(NodeType.QUESTION)
        {
            this.ID = id;
            this.Question = question;
            this.QuestionType = questionType;
        }

        public override string ToString()
        {
            return base.ToString() + string.Format("\tID: {0}\t{1}\t\t{2}", ID, QuestionType, Question);
        }
    }
}
