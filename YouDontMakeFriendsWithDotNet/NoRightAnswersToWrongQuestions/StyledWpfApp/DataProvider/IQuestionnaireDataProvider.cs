using QlsTransformer.Orchestration.Models;
using QlsTransformer.UI.Models;

namespace StyledWpfApp.DataProvider
{
    public interface IQuestionnaireDataProvider
    {
        StyledQuestionnaireWrapper GetSingleQuestionnaire();
        void LoadDefaultQuestionnaire();
        void Reload(StyledQuestionnaireModel questionnaireModel);
    }
}
