using System.Collections.ObjectModel;
using QlsTransformer.UI.Models;
using QuestionnaireUI.Models;

namespace StyledWpfApp.ViewModels
{
    public class PageViewModel: Observable, IPageViewModel
    {
        public PageViewModel(PageWrapper page)
        {
            foreach (var sectionWrapper in page.Sections)
            {
                Sections.Add(new SectionViewModel(sectionWrapper));
            }
        }

        public ObservableCollection<ISectionViewModel> Sections { get; } 
            = new ObservableCollection<ISectionViewModel>();
    }
}
