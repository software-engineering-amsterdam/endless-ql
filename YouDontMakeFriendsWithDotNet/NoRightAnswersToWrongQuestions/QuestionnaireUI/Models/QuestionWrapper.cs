using System;
using System.CodeDom;
using System.Runtime.CompilerServices;

namespace QuestionnaireUI.Models
{
    public class QuestionWrapper : ModelWrapperBase<QuestionModel>
    {
        public Guid QuestionId => Model.QuestionId;
        public string QuestionText => Model.QuestionText;
        public Type QuestionType => Model.QuestionType;

        public string Value
        {
            get { return Model.Value; }
            set
            {
                SetValue(value);
            }
        }
        
        public bool ReadOnly => Model.ReadOnly;

        public bool Visible => Model.Visible;

        public QuestionWrapper(QuestionModel model) : base(model)
        {
        }
    }
}
