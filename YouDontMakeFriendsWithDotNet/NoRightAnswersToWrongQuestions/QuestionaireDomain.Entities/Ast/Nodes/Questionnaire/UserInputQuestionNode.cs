using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire
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