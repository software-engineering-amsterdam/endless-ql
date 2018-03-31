using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators
{
    public class UnknownQuestionValidationMetaData : ValidationMetaData
    {
        public UnknownQuestionValidationMetaData()
            : base(Severity.Error)
        {
        }
    }
}