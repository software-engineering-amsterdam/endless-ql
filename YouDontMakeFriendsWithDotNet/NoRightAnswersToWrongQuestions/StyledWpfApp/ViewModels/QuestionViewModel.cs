using System.ComponentModel;
using System.Windows.Input;
using QlsTransformer.UI.Models;
using QuestionnaireUI;
using QuestionnaireUI.Models;

namespace StyledWpfApp.ViewModels
{
    public class QuestionViewModel : Observable, IQuestionViewModel
    {
        public QuestionViewModel(StyledQuestionWrapper question)
        {
            m_question = question;
            DataChangedCommand = new DelegateCommand(OnDataChangedCommand);
            m_question.PropertyChanged += OnDataChangedCommand;
        }

        private void OnDataChangedCommand(object sender, PropertyChangedEventArgs e)
        {
            OnDataChangedCommand(null);
        }

        private void OnDataChangedCommand(object obj)
        {
            m_question.PropertyChanged -= OnDataChangedCommand;

            //ToDo: something here to load data
            
            m_question.PropertyChanged += OnDataChangedCommand;
        }
        
        public ICommand DataChangedCommand { get; private set; }
        private StyledQuestionWrapper m_question;

        public StyledQuestionWrapper Question
        {
            get { return m_question; }
            private set
            {
                m_question = value;
                RaisePropertyChanged();
            }
        }
    }
}