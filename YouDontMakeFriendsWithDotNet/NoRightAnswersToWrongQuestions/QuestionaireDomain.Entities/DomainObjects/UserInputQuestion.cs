using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class UserInputQuestion : AstNodeBase, IUserInputQuestionNode
    {
        public string QuestionId { get; }
        public string QuestionText { get; }
        public Type QuestionType { get; }

        public UserInputQuestion(
            Guid id, 
            string definition,
            string questionId, 
            string questionText, 
            Type questionType) : base(id, definition)
        {
            QuestionId = questionId;
            QuestionText = questionText;
            QuestionType = questionType;
        }

        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: Do something here
        }
    }
}