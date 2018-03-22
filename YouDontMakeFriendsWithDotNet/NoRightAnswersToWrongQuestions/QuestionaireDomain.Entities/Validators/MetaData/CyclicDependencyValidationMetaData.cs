namespace QuestionnaireDomain.Entities.Validators.MetaData
{
    public class CyclicDependencyValidationMetaData : ValidationMetaData
    {
        public CyclicDependencyValidationMetaData()
            : base(Severity.Error)
        {
        }
    }
}