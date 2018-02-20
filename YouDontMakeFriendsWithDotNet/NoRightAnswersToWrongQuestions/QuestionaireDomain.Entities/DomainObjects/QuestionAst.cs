using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class QuestionAst : AstNodeBase, IQuestionAst
    {
        public QuestionAst(string name, string text)
        {
            Name = name;
            Text = text;
        }

        public string Name { get; }
        public string Text { get; }


        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: Do something here
        }
    }
}
