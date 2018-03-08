using System.Collections.ObjectModel;
using Prism.Events;

namespace QuestionnaireWPFApp.ViewModels
{
    public class MainViewModel : Observable, IMainViewModel
    {
        public ObservableCollection<IQuestionnaireViewModel> QuestionaireViewModels { get; private set; }
        public INavigationViewModel NavigationViewModel { get; }

        public MainViewModel(INavigationViewModel navigationViewModel)
        {

            NavigationViewModel = navigationViewModel;
        }

        public void Load()
        {
            NavigationViewModel.Load();
        }
    }
}
