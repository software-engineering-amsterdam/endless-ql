using System;
using System.Collections.ObjectModel;
using System.Linq;
using QuestionnaireOrchestration.Models;

namespace QuestionnaireUI.Models
{
    public class QuestionnaireWrapper : ModelWrapperBase<QuestionnaireModel>
    {
        public QuestionnaireWrapper(QuestionnaireModel model) : base(model)
        {
            InitializeCollectionProperties(model);
        }

        public Guid QuestionnaireId => GetValue<Guid>();
        public string QuestionnaireDisplayName => GetValue<string>();

        public ObservableCollection<QuestionWrapper> Questions { get; private set; }

        private void InitializeCollectionProperties(QuestionnaireModel model)
        {
            if (model.Questions == null) throw new ArgumentException("questions cannot be null");

            Questions = new ObservableCollection<QuestionWrapper>(
                model.Questions.Select(q => new QuestionWrapper(q)));

            RegisterCollection(Questions, model.Questions);
        }
    }
}