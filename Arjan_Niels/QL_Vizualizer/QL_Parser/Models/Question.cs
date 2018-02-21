namespace QL_Parser.Models
{
    public class Question : Section
    {
        public string Text { get; set; }
        public string ID { get; set; }
        public string QType { get; set; }

        public override string ToString()
        {
            return string.Format("ID: {0}\t{1}\t\t{2}", ID, QType, Text);
        }
    }
}
