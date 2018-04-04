using System.Collections.ObjectModel;

namespace StyledWpfApp.ViewModels
{
    public interface IPageViewModel
    {
        string Name { get; }

        ObservableCollection<ISectionViewModel> Sections { get; }
    }
}