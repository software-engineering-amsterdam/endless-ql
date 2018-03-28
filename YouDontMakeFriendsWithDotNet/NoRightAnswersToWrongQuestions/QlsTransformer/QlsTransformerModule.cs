using Microsoft.Extensions.DependencyInjection;
using QlsTransformer.Ast.Tools;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer
{
    public class QlsTransformerModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IStyleSheetCreator), typeof(StyleSheetCreator));
        }
    }
}
