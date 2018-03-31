using Microsoft.Extensions.DependencyInjection;
using QlsTransformer.Ast.Tools;
using QlsTransformer.Orchestration.QueryServices;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer
{
    public class QlsTransformerModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(
                typeof(IStyleSheetCreator), 
                typeof(StyleSheetCreator));

            appRegistration.AddSingleton(
                typeof(IQlsAstFactory), 
                typeof(QlsAstFactory));

            appRegistration.AddSingleton(
                typeof(IStyledQuestionnaireModelQueryService),
                typeof(StyledQuestionnaireModelQueryService));

        }
    }
}
