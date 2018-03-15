using System;
using System.Collections.Generic;

namespace QuestionnaireUI.Models
{
    public class QuestionnaireWrapper : ModelWrapperBase<QuestionnaireModel>
    {
        public Guid QuestionnaireId => GetValue<Guid>();
        public string QuestionnaireDisplayName => GetValue<string>();
        public IList<QuestionModel> Questions => GetValue<IList<QuestionModel>>();

        public QuestionnaireWrapper(QuestionnaireModel model) : base(model)
        {
        }
    }
}