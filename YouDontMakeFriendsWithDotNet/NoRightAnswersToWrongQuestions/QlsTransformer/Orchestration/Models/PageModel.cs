using System;
using System.Collections.Generic;

namespace QlsTransformer.Orchestration.Models
{
    public class PageModel
    {
        public PageModel(
            Guid pageId,
            string pageDisplayName)
        {
            PageId = pageId;
            PageDisplayName = pageDisplayName;
        }

        public Guid PageId { get; }
        public string PageDisplayName { get; }
        public IList<SegmentModel> Segments { get; }
            = new List<SegmentModel>();
    }
}