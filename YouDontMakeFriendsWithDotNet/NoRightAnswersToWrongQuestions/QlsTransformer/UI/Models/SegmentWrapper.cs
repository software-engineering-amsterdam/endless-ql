using System;
using System.Collections.ObjectModel;
using System.Linq;
using QlsTransformer.Orchestration.Models;
using QuestionnaireUI.Models;

namespace QlsTransformer.UI.Models
{
    public class SegmentWrapper : ModelWrapperBase<SegmentModel>
    {
        public SegmentWrapper(SegmentModel model) : base(model)
        {
            InitializeCollectionProperties(model);
        }

        public Guid SegmentId => GetValue<Guid>();
        public string SegmentDisplayName => GetValue<string>();

        public ObservableCollection<StyledQuestionWrapper> Questions { get; private set; }

        private void InitializeCollectionProperties(SegmentModel model)
        {
            if (model.Questions == null)
            {
                throw new ArgumentException("questions cannot be null");
            }

            Questions = new ObservableCollection<StyledQuestionWrapper>(
                model.Questions.Select(x => new StyledQuestionWrapper(x)));

            RegisterCollection(Questions, model.Questions);
        }
    }
}