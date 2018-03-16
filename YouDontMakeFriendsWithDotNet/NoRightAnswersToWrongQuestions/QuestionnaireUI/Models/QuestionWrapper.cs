using System;
using QuestionaireOrchestration.Models;

namespace QuestionnaireUI.Models
{
    public class QuestionWrapper : ModelWrapperBase<QuestionModel>
    {
        public Guid QuestionOutputId => GetValue<Guid>();
        public Guid QuestionVariableId => GetValue<Guid>();
        public string QuestionText => GetValue<string>();
        public Type QuestionType => GetValue<Type>();
        public bool ReadOnly => GetValue<bool>();
        public bool Visible => GetValue<bool>();

        public string Value
        {
            get { return GetValue<string>(); }
            set { SetValue(value); }
        }

        public QuestionWrapper(QuestionModel model) : base(model)
        {
        }
    }
}
