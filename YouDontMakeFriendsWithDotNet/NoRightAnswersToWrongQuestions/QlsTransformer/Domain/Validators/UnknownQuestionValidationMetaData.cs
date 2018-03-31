using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Validators
{
    public class UnknownQuestionValidationMetaData : ValidationMetaData
    {
        public UnknownQuestionValidationMetaData()
            : base(Severity.Error)
        {
        }
    }
}