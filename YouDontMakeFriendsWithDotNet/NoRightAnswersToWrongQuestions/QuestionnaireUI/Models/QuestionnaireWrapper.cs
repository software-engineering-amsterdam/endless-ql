using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;

namespace QuestionnaireUI.Models
{
    public class QuestionnaireWrapper : ModelWrapperBase<QuestionnaireModel>
    {
        public Guid QuestionnaireId => GetValue<Guid>();
        public string QuestionnaireDisplayName => GetValue<string>();

        public ObservableCollection<QuestionWrapper> Questions { get; private set; }

        public QuestionnaireWrapper(QuestionnaireModel model) : base(model)
        {
            InitializeCollectionProperties(model);
        }

        private void InitializeCollectionProperties(QuestionnaireModel model)
        {
            if (model.Questions == null)
            {
                throw new ArgumentException("questions cannot be null");
            }

            Questions = new ObservableCollection<QuestionWrapper>(
                model.Questions.Select(q => new QuestionWrapper(q)));
        }
    }
}