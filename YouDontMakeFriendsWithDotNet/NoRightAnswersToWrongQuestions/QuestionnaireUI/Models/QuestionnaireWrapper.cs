using System;
using System.Collections.Generic;

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

        public QuestionnaireModel Model { get; }
        public Guid QuestionnaireId => Model.QuestionnaireId;
        public string QuestionnaireDisplayName => Model.QuestionnaireDisplayName;
        public IList<QuestionModel> Questions => Model.Questions;
    }
}