using System.ComponentModel;
using System.Windows.Input;
using QuestionnaireUI.Models;
using SimpleWPFApp.DataProvider;

namespace SimpleWPFApp
{
    public class QuestionnaireViewModel : Observable, IQuestionnaireViewModel
    {
        private readonly IQuestionnaireDataProvider m_dataProvider;

        public QuestionnaireViewModel(
            IQuestionnaireDataProvider dataProvider)
        {
            m_dataProvider = dataProvider;
        }

        public void Load()
        {
            m_dataProvider.LoadDefaultQuestionnaire();
            Questionnaire = m_dataProvider.GetSingleQuestionnaire();
            DataChangedCommand = new DelegateCommand(OnDataChangedCommand);
            foreach (var question in Questionnaire.Questions)
            {
                question.PropertyChanged += OnDataChangedCommand;
            }
        }

        private void OnDataChangedCommand(object sender, PropertyChangedEventArgs e)
        {
            OnDataChangedCommand(null);
        }

        private void OnDataChangedCommand(object obj)
        {
            foreach (var question in Questionnaire.Questions)
            {
                question.PropertyChanged -= OnDataChangedCommand;
            }

            m_dataProvider.Reload(Questionnaire.Model);
            Questionnaire = m_dataProvider.GetSingleQuestionnaire();

            foreach (var question in Questionnaire.Questions)
            {
                question.PropertyChanged += OnDataChangedCommand;
            }
        }

        public ICommand DataChangedCommand { get; private set; }

        private QuestionnaireWrapper m_questionnaire;

        public QuestionnaireWrapper Questionnaire
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
