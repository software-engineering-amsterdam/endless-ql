using System.ComponentModel;
using System.Windows.Input;
using QlsTransformer.UI.Models;
using QuestionnaireUI.Models;
using StyledWpfApp.DataProvider;

namespace StyledWpfApp.ViewModels
{
    public class StyledQuestionnaireViewModel : Observable, IStyledQuestionnaireViewModel
    {
        private readonly IQuestionnaireDataProvider m_dataProvider;

        public StyledQuestionnaireViewModel(
            IQuestionnaireDataProvider dataProvider)
        {
            m_dataProvider = dataProvider;
        }

        public void Load()
        {
            m_dataProvider.LoadDefaultQuestionnaire();
            Questionnaire = m_dataProvider.GetSingleQuestionnaire();
            DataChangedCommand = new DelegateCommand(OnDataChangedCommand);
            foreach (var page in Questionnaire.Pages)
            {
                page.PropertyChanged += OnDataChangedCommand;
            }
        }

        private void OnDataChangedCommand(object sender, PropertyChangedEventArgs e)
        {
            OnDataChangedCommand(null);
        }

        private void OnDataChangedCommand(object obj)
        {
            foreach (var page in Questionnaire.Pages)
            {
                page.PropertyChanged -= OnDataChangedCommand;
            }

            m_dataProvider.Reload(Questionnaire.Model);
            Questionnaire = m_dataProvider.GetSingleQuestionnaire();

            foreach (var page in Questionnaire.Pages)
            {
                page.PropertyChanged += OnDataChangedCommand;
            }
        }

        public ICommand DataChangedCommand { get; private set; }
        private StyledQuestionnaireWrapper m_questionnaire;

        public StyledQuestionnaireWrapper Questionnaire
        {
            get { return m_questionnaire; }
            private set
            {
                m_questionnaire = value;
                RaisePropertyChanged();
            }
        }
    }
}