using Microsoft.Extensions.DependencyInjection;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic
{
    public class DomainLogicModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IQuestionnaireCreator), typeof(QuestionnaireCreator));
            appRegistration.AddSingleton(typeof(IDomainItemLocator), typeof(DomainItemLocator));
            appRegistration.AddSingleton(typeof(DomainItemRegistry));
        }
    }
}
