using Microsoft.Extensions.DependencyInjection;
using QlsTransformer.Domain.Ast.Tools;
using QlsTransformer.Domain.Output.Tools;
using QlsTransformer.Domain.Validators;
using QlsTransformer.Domain.Validators.CorrectWidgetForDefaults;
using QlsTransformer.Domain.Validators.UnkownQuestion;
using QlsTransformer.Orchestration.CommandHandler;
using QlsTransformer.Orchestration.Commands;
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
                typeof(IStyleSheetTypeChecker),
                typeof(StyleSheetTypeChecker));

            appRegistration.AddSingleton(
                typeof(IQlsAstFactory), 
                typeof(QlsAstFactory));

            appRegistration.AddSingleton(
                typeof(IStyledQuestionnaireModelQueryService),
                typeof(StyledQuestionnaireModelQueryService));

            appRegistration.AddTransient(
                typeof(ICommandHandler<UpdateStyledValuesCommand>),
                typeof(UpdateStyledValuesCommandHandler));

            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateStyleSheetFromTextCommand>),
                typeof(CreateStyleSheetFromTextCommandHandler));

            appRegistration.AddSingleton(
                typeof(IUnknownQuestionValidator), 
                typeof(UnknownQuestionValidator));

            appRegistration.AddSingleton(
                typeof(IStyledOutputItemFactory),
                typeof(StyledOutputItemFactory));

            appRegistration.AddSingleton(
                typeof(IQuestionStyleVisitor),
                typeof(QuestionStyleVisitor));

            appRegistration.AddSingleton(
                typeof(IStyledQuestionnaireOutputCreator),
                typeof(StyledQuestionnaireOutputCreator));
            
            appRegistration.AddSingleton(
                typeof(IStyleFactory),
                typeof(StyleFactory));
            
            appRegistration.AddSingleton(
                typeof(ICorrectWidgetValidator),
                typeof(CorrectWidgetValidator));
        }
    }
}
