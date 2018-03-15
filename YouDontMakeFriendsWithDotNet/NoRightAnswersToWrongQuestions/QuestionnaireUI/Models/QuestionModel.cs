using System;

namespace QuestionnaireUI.Models
{
    public class QuestionModel
    {
        public Guid QuestionId { get; set; }
        public string DisplayName { get; set; }
        public string QuestionText { get; set; }
        public bool Visible { get; set; }
        public bool ReadOnly { get; set; }
        public string Value { get; set; }
        public Type QuestionType { get; set; }
    }
}
