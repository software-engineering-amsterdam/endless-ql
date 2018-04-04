using System.Collections.ObjectModel;
using QlsTransformer.UI.Models;
using QuestionnaireUI.Models;

namespace StyledWpfApp.ViewModels
{
    public class PageViewModel: Observable, IPageViewModel
    {
        public PageViewModel(PageWrapper page)
        {
            Name = page.PageDisplayName;
            foreach (var sectionWrapper in page.Sections)
            {
                Sections.Add(new SectionViewModel(sectionWrapper));
            }
        }

        public string Name { get; private set; }

        public ObservableCollection<ISectionViewModel> Sections { get; } 
            = new ObservableCollection<ISectionViewModel>();
    }
}
