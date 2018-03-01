using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class Question : AstNodeBase, IQuestionNode
    {
        public Question(Guid id, string questionId, string questionText, Type questionType) : base(id)
        {
            QuestionId = questionId;
            QuestionText = questionText;
            QuestionType = questionType;
        }

        public string QuestionId { get; }
        public string QuestionText { get; }
        public Type QuestionType { get;  } 

        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: Do something here
        }
    }
}