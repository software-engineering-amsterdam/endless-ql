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

        public ObservableCollection<SegmentWrapper> Segments { get; private set; }

        private void InitializeCollectionProperties(PageModel model)
        {
            if (model.Segments == null)
            {
                throw new ArgumentException("segments cannot be null");
            }

            Segments = new ObservableCollection<SegmentWrapper>(
                model.Segments.Select(x => new SegmentWrapper(x)));

            RegisterCollection(Segments, model.Segments);
        }
    }
}