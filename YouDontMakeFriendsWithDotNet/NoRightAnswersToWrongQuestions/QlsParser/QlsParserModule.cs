using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;
using QlsParser.AstBuilder;
using QlsTransformer.Ast.Tools;
using QuestionnaireInfrastructure.API;

namespace QlsParser
{
    public class QlsParserModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IQlsAstBuilder), typeof(QlsAstBuilder));
        }
    }
}