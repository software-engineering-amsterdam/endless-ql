using System.Collections.ObjectModel;
using QlsTransformer.UI.Models;

namespace StyledWpfApp.ViewModels
{
    public class SectionViewModel : ISectionViewModel
    {
        public SectionViewModel(SectionWrapper section)
        {
            foreach (var questionWraper in section.Questions)
            {
                Questions.Add(questionWraper);
            }
        }

        public ObservableCollection<StyledQuestionWrapper> Questions { get; } = new ObservableCollection<StyledQuestionWrapper>();
    }
}
