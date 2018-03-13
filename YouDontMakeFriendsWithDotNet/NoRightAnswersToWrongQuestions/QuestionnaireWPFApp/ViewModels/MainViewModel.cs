using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows.Controls;
using Prism.Events;
using QuestionnaireWPFApp.Events;

namespace QuestionnaireWPFApp.ViewModels
{
    public class MainViewModel : Observable, IMainViewModel
    {
        private readonly IEventAggregator m_eventAggregator;
        private readonly IMessageDialogService m_messageDialogService;
        private IQuestionnaireViewModel m_selectedQuestionaireEditViewModel;

        public ObservableCollection<IQuestionnaireViewModel> QuestionaireViewModels { get; private set; }
        public INavigationViewModel NavigationViewModel { get; }

        public MainViewModel(
            IEventAggregator eventAggregator, 
            INavigationViewModel navigationViewModel,
            IMessageDialogService messageDialogService)
        {
            m_eventAggregator = eventAggregator;
            m_messageDialogService = messageDialogService;
            m_eventAggregator
                .GetEvent<OpenQuestionnaireEditViewEvent>()
                .Subscribe(OnOpenQuestionnaireTab);

            NavigationViewModel = navigationViewModel;
        }


        private void OnOpenQuestionnaireTab(Guid questionnaireId)
        {
            var questionnaireEditVm = QuestionaireViewModels
                .SingleOrDefault(vm => vm.Questionnaire.Id == questionnaireId);

            if (questionnaireEditVm == null)
            {
                //ToDo: how do we load new ones?
                throw new NotImplementedException("not implemented new questionnaires");
            }

            SelectedQuestionaireEditViewModel = questionnaireEditVm;
        }

        public IQuestionnaireViewModel SelectedQuestionaireEditViewModel
        {
            get { return m_selectedQuestionaireEditViewModel; }
            set
            {
                m_selectedQuestionaireEditViewModel = value;
                RaisePropertyChanged();
            }
        }

        public void Load()
        {
            NavigationViewModel.Load();
        }
    }
}
