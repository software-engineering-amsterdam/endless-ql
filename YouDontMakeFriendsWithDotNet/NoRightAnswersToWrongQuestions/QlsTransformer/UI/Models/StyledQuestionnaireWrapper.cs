using System;
using System.Collections.ObjectModel;
using System.Linq;
using QlsTransformer.Orchestration.Models;
using QuestionnaireUI.Models;

namespace QlsTransformer.UI.Models
{
    public class StyledQuestionnaireWrapper : ModelWrapperBase<StyledQuestionnaireModel>
    {
        public StyledQuestionnaireWrapper(StyledQuestionnaireModel model)
            : base(model)
        {
            InitializeCollectionProperties(model);
        }

        public Guid QuestionnaireId => GetValue<Guid>();
        public string QuestionnaireDisplayName => GetValue<string>();

        public ObservableCollection<PageWrapper> Pages { get; private set; }

        private void InitializeCollectionProperties(StyledQuestionnaireModel model)
        {
            if (model.Pages == null) throw new ArgumentException("pages cannot be null");

            Pages = new ObservableCollection<PageWrapper>(
                model.Pages.Select(x => new PageWrapper(x)));

            RegisterCollection(Pages, model.Pages);
        }
    }
}