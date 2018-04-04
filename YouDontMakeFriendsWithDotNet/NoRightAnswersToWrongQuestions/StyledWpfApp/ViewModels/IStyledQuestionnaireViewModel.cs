using System.Collections.ObjectModel;

namespace StyledWpfApp.ViewModels
{
    public interface IStyledQuestionnaireViewModel
    {
        void Load();
        int SelectedPageNumber { get; set; }
        ObservableCollection<IPageViewModel> Pages { get; }
        string Name { get; }
    }
}
