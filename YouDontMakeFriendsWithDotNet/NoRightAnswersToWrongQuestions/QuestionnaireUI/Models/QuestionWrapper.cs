using System;
using System.CodeDom;
using System.Runtime.CompilerServices;

namespace QuestionnaireUI.Models
{
    public class QuestionWrapper : ModelWrapperBase<QuestionModel>
    {
        public Guid QuestionId => GetValue<Guid>();
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
