using QuestionaireDomain.Entities.API.Output;

namespace QuestionaireOrchestration.API
{
    public static class ModelExtensions
    {
        public static ModelReference<T> ToModel<T>(this IOutputItem domainItem) where T : IOutputItem
        {
            return new ModelReference<T>(domainItem.Id, domainItem.DisplayName);
        }
    }
}