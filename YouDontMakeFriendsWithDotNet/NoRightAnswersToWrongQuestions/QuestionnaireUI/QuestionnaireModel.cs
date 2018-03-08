using System;
using System.Collections.Generic;

namespace QuestionnaireUI
{
    public class QuestionnaireModel
    {
        public IList<CalculatedQuestionModel> Statements { get; set; } 
            = new List<CalculatedQuestionModel>();

        public Guid Id { get; set; }
        public string Name { get; set; }
    }
}