using QuestionnaireDomain.Entities.Validators;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QlsTransformer.Domain.Validators.CorrectWidgetForDefaults
{
    public class CorrectWidgetValidationMetaData : ValidationMetaData
    {
        public CorrectWidgetValidationMetaData()
            : base(Severity.Error)
        {
        }
    }
}