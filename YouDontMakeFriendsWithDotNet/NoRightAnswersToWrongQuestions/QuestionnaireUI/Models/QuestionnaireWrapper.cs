using System;

namespace QuestionnaireUI.Models
{
    public class QuestionnaireWrapper
    {
        public QuestionnaireWrapper(QuestionnaireModel model)
        {
            if (model == null)
            {
                throw new ArgumentNullException(nameof(model));
            }
            Model = model;
        }

        public QuestionnaireModel Model { get; set; }
    }
}