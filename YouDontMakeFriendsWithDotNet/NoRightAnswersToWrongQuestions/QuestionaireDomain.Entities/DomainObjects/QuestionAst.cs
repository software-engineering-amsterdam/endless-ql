using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class QuestionAst : IQuestionAst
    {
        public QuestionAst(string name, string text)
        {
            Name = name;
            Text = text;
        }

        public string Name { get; }
        public string Text { get; }
    }
}
