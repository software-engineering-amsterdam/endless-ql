using Microsoft.Extensions.DependencyInjection;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireDomain.Logic
{
    public class DomainLogicModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IQuestionnaireModelCreator), typeof(QuestionnaireModelCreator));
            appRegistration.AddSingleton(typeof(IQuestionnaireAstCreator), typeof(QuestionnaireAstCreator));
        }
    }
}