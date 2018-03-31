using System.Collections.ObjectModel;
using QlsTransformer.UI.Models;

namespace StyledWpfApp.ViewModels
{
    public class SectionViewModel : ISectionViewModel
    {
        public SectionViewModel(SectionWrapper section)
        {
            foreach (var sectionWrapper in section.Questions)
            {
                Questions.Add(new QuestionViewModel(sectionWrapper));
            }
        }

        public ObservableCollection<QuestionViewModel> Questions = new ObservableCollection<QuestionViewModel>();
    }
}
