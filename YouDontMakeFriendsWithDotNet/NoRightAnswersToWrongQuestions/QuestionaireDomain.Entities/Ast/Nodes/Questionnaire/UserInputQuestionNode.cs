using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire
{
    internal class UserInputQuestionNode : 
        AstNodeBase, 
        IUserInputQuestionNode
    {
        public string QuestionId { get; }
        public string QuestionText { get; }
        public Type QuestionType { get; }

        public UserInputQuestionNode(
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
    }
}