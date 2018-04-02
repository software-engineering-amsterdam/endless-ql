using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireOrchestration.Models
{
    public class QuestionModel
    {
        public QuestionModel(
            Guid questionOutputId,
            Guid questionVariableId,
            string questionText,
            bool visible,
            bool readOnly,
            IQuestionType questionType)
        {
            QuestionVariableId = questionVariableId;
            QuestionOutputId = questionOutputId;
            QuestionText = questionText;
            Visible = visible;
            ReadOnly = readOnly;
            QuestionType = questionType;
        }

        public Guid QuestionOutputId { get; }
        public Guid QuestionVariableId { get; }
        public string QuestionText { get; }
        public bool Visible { get; }
        public bool ReadOnly { get; }
        public dynamic Value { get; set; }
        public IQuestionType QuestionType { get; }
    }
}