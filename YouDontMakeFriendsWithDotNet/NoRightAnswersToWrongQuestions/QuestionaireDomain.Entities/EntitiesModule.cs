using System;
using Microsoft.Extensions.DependencyInjection;
using QuestionnaireDomain.Entities.Ast.Tools;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireDomain.Entities
{
    public class EntitiesModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IAstFactory), typeof(AstFactory));
            appRegistration.AddSingleton(typeof(IOutputItemFactory), typeof(OutputItemFactory));
            appRegistration.AddSingleton(typeof(IDomainItemRegistry), typeof(DomainItemRegistry));
            appRegistration.AddSingleton(typeof(ISymbolTable), typeof(SymbolTable));
            appRegistration.AddSingleton(typeof(IBooleanEvaluatorVisitor), typeof(BooleanEvaluatorVisitor));
            appRegistration.AddSingleton(typeof(ICalculationVisitor), typeof(CalculationVisitor));
            appRegistration.AddSingleton(typeof(IBuildOutputVisitor), typeof(BuildOutputVisitor));
            appRegistration.AddSingleton(typeof(IDomainItemLocator), typeof(DomainItemLocator));
            appRegistration.AddSingleton(typeof(IVariableUpdater), typeof(VariableUpdater));
            appRegistration.AddSingleton(typeof(IQuestionnaireModelCreator), typeof(QuestionnaireModelCreator));
            appRegistration.AddSingleton(typeof(IQuestionnaireAstCreator), typeof(QuestionnaireAstCreator));
        }
    }
}
