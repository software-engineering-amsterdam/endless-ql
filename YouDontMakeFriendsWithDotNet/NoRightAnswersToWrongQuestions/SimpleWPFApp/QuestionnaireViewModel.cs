using QuestionnaireUI.Models;

namespace SimpleWPFApp
{
    public interface IQuestionnaireViewModel
    {
        void Load();
    }

    public class QuestionnaireViewModel : Observable
    {
        public QuestionnaireViewModel()
        {
        }

        public void Load()
        {
        }
    }
}
