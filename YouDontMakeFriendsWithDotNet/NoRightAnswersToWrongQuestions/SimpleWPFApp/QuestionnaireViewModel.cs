using System;
using System.Windows.Input;
using QuestionnaireUI.Models;
using SimpleWPFApp.DataProvider;

namespace SimpleWPFApp
{
    public interface IQuestionnaireViewModel
    {
        void Load();
        QuestionnaireWrapper Questionnaire { get;  }
    }

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
                question.PropertyChanged += (s, e) => { OnDataChangedCommand(null); };
            }
        }

        private void OnDataChangedCommand(object obj)
        {
            m_dataProvider.Reload(Questionnaire.Model);
            Questionnaire = m_dataProvider.GetSingleQuestionnaire();
            foreach (var question in Questionnaire.Questions)
            {
                question.PropertyChanged += (s, e) => { OnDataChangedCommand(null); };
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
