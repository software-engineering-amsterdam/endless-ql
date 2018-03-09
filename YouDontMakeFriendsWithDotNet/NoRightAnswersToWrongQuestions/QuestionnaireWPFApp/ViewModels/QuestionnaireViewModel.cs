using System;
using QuestionnaireUI;

namespace QuestionnaireWPFApp.ViewModels
{
    public interface IQuestionnaireViewModel
    {
        void Load(Guid? id = null);
        QuestionnaireWrapper Questionnaire { get; }
    }

    public class QuestionnaireViewModel : Observable, IQuestionnaireViewModel
    {
        private QuestionnaireWrapper m_questionnaire;

        public void Load(Guid? id = null)
        {
            throw new NotImplementedException();
        }

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
