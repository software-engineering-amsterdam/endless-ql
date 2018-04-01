using QuestionnaireUI.Models;

namespace SimpleWPFApp
{
    public interface IQuestionnaireViewModel
    {
        void Load();
        QuestionnaireWrapper Questionnaire { get;  }
    }
}