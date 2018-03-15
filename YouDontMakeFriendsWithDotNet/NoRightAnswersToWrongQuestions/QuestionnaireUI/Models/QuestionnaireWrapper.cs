using System;
using System.Collections.Generic;

namespace QuestionnaireUI.Models
{
    public class QuestionnaireWrapper : ModelWrapperBase<QuestionnaireModel>
    {
        public Guid QuestionnaireId => Model.QuestionnaireId;
        public string QuestionnaireDisplayName => Model.QuestionnaireDisplayName;
        public IList<QuestionModel> Questions => Model.Questions;

        public QuestionnaireWrapper(QuestionnaireModel model) : base(model)
        {
        }
    }
}