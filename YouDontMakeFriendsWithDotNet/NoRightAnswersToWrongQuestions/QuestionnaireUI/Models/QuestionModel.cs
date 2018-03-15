using System;

namespace QuestionnaireUI.Models
{
    public class QuestionModel
    {
        public QuestionModel(
            Guid questionId, 
            string questionText, 
            bool visible, 
            bool readOnly, 
            Type questionType)
        {
            QuestionId = questionId;
            QuestionText = questionText;
            Visible = visible;
            ReadOnly = readOnly;
            QuestionType = questionType;
        }

        public Guid QuestionId { get; }
        public string QuestionText { get; }
        public bool Visible { get; }
        public bool ReadOnly { get; }
        public string Value { get; set; }
        public Type QuestionType { get; }
    }
}
