using System.Collections.ObjectModel;
using Prism.Events;

namespace QuestionnaireWPFApp.ViewModels
{
    public class MainViewModel : Observable
    {
        public ObservableCollection<IQuestionnaireViewModel> QuestionaireViewModels { get; private set; }

        public MainViewModel()
        {

        }
    }
}
