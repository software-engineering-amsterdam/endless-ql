namespace QuestionnaireWPFApp.ViewModels
{
    public class MainViewModel : Observable, IMainViewModel
    {
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
