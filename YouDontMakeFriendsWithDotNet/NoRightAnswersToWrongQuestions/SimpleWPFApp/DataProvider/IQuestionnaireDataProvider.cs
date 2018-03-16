using QuestionaireOrchestration.Models;
using QuestionnaireUI.Models;

namespace SimpleWPFApp.DataProvider
{
    public interface IQuestionnaireDataProvider
    {
        QuestionnaireWrapper GetSingleQuestionnaire();
        void LoadDefaultQuestionnaire();
        void Reload(QuestionnaireModel questionnaireModel);
    }
}
