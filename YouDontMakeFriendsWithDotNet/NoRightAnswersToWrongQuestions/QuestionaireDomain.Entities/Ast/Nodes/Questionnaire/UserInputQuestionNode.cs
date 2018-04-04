using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire
{
    internal class UserInputQuestionNode :
        AstNodeBase,
        IUserInputQuestionNode
    {
        public UserInputQuestionNode(
            Guid id,
            string definition,
            string questionId,
            string questionText,
            IQuestionType questionType) : base(id, definition)
        {
            QuestionName = questionId;
            QuestionText = questionText;
            QuestionType = questionType;
        }

        public string QuestionName { get; }
        public string QuestionText { get; }
        public IQuestionType QuestionType { get; }
    }
}