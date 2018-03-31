using System.Collections.ObjectModel;

namespace StyledWpfApp.ViewModels
{
    public interface IStyledQuestionnaireViewModel
    {
        void Load();
        ObservableCollection<IPageViewModel> Pages { get; }
    }
}
