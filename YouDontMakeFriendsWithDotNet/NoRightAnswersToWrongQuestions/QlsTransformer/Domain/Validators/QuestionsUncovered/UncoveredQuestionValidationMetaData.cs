using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators.QuestionsUncovered
{
    public class UncoveredQuestionValidationMetaData : ValidationMetaData
    {
        public UncoveredQuestionValidationMetaData()
            : base(Severity.Error)
        {
        }
    }
}