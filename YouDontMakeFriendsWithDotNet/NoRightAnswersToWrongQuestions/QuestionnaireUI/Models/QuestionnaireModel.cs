using System;
using System.Collections.Generic;

namespace QuestionnaireUI.Models
{
    public class QuestionnaireModel
    {
        public Guid QuestionnaireId { get; set; }
        public string QuestionnaireDisplayName { get; set; }
        public IList<QuestionModel> Questions { get; set; }
            = new List<QuestionModel>();
    }
}