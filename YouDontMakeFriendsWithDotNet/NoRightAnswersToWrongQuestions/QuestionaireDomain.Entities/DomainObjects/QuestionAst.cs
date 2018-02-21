using System;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class QuestionAst : AstNodeBase, IQuestionAst
    {
        public QuestionAst(string name, string text, Type type)
        {
            Name = name;
            Text = text;
            Type = type;
        }

        public string Name { get; }
        public string Text { get; }
        public Type Type { get;  } 

        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: Do something here
        }
    }
}
