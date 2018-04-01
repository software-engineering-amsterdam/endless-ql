using QlsTransformer.Orchestration.Models;
using QlsTransformer.UI.Models;

namespace StyledWpfApp.DataProvider
{
    public interface IQuestionnaireDataProvider
    {
        StyledQuestionnaireWrapper GetSingleQuestionnaire();
        void LoadDefaultQuestionnaire();
        void LoadStyleSheet();
        void Reload(StyledQuestionnaireModel questionnaireModel);
    }
}
