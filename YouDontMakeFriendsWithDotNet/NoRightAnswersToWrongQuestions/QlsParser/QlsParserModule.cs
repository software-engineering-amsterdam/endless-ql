using Microsoft.Extensions.DependencyInjection;
using QlsParser.AstBuilder;
using QlsTransformer.Domain.Ast.Tools;
using QuestionnaireInfrastructure.API;

namespace QlsParser
{
    public class QlsParserModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(
                typeof(IQlsAstBuilder), 
                typeof(QlsAstBuilder));
        }
    }
}