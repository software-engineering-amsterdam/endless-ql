using System;
using System.Collections.ObjectModel;
using System.Linq;
using QlsTransformer.Orchestration.Models;
using QuestionnaireUI.Models;

namespace QlsTransformer.UI.Models
{
    public class PageWrapper : ModelWrapperBase<PageModel>
    {
        public PageWrapper(PageModel model) : base(model)
        {
            InitializeCollectionProperties(model);
        }

        public Guid PageId => GetValue<Guid>();
        public string PageDisplayName => GetValue<string>();

        public ObservableCollection<SectionWrapper> Sections { get; private set; }

        private void InitializeCollectionProperties(PageModel model)
        {
            if (model.Sections == null)
            {
                throw new ArgumentException("segments cannot be null");
            }

            Sections = new ObservableCollection<SectionWrapper>(
                model.Sections.Select(x => new SectionWrapper(x)));

            RegisterCollection(Sections, model.Sections);
        }
    }
}