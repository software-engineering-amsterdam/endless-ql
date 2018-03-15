using System;

namespace QuestionnaireUI.Models
{
    public class QuestionWrapper : Observable
    {
        public QuestionWrapper(QuestionModel model)
        {
            if (model == null)
            {
                throw new ArgumentNullException(nameof(model));
            }

            Model = model;
        }

        public QuestionModel Model { get;  }
        public Guid QuestionId => Model.QuestionId;
        public string QuestionText => Model.QuestionText;
        public Type QuestionType => Model.QuestionType;
        public bool ReadOnly => Model.ReadOnly;
        public bool Visible => Model.Visible;
        public string Value
        {
            set
            {
                if (value != Model.Value)
                {
                    Model.Value = value;
                    RaisePropertyChanged();
                }
            }
            get { return Model.Value; }
        }
    }
}
