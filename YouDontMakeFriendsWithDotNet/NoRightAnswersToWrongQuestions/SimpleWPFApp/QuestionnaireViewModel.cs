using System;
using System.ComponentModel;
using System.Linq;
using System.Windows.Input;
using QuestionaireOrchestration.Commands;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices;
using QuestionnaireInfrastructure.API;
using QuestionnaireUI.Models;
using SimpleWPFApp.Properties;

namespace SimpleWPFApp
{
    public class QuestionnaireViewModel : Observable, IQuestionnaireViewModel
    {
        private readonly ICommandBus m_commandBus;
        private readonly IQuestionnaireOutputModelQueryService m_questionnaireQueryService;
        private QuestionnaireWrapper m_questionnaire;

        public QuestionnaireWrapper Questionnaire
        {
            get { return m_questionnaire; }
            private set
            {
                m_questionnaire = value;
                RaisePropertyChanged();
            }
        }

        public QuestionnaireViewModel(
            ICommandBus commandBus,
            IQuestionnaireOutputModelQueryService questionnaireQueryService)
        {
            m_commandBus = commandBus;
            m_questionnaireQueryService = questionnaireQueryService;

            ReloadCommand = new DelegateCommand(OnReload);
        }
        
        public ICommand ReloadCommand { get; }

        private void OnReload(object obj)
        {
            var command = new UpdateValuesCommand
            {
                Questionnaire = Questionnaire.Model
            };
            m_commandBus.Send(command);
            Reload();
        }

        public void Load()
        {
            var command = new CreateDefinitionFromTextCommand
            {
                DefinitionText = Resources.ExampleForm
            };

            m_commandBus.Send(command);
            Reload();
        }

        private void Reload()
        {
            var questionnaireDefinitionReference = m_questionnaireQueryService
                .GetAll()
                .FirstOrDefault();

            if (questionnaireDefinitionReference == null)
            {
                throw new ArgumentException("questionnaire not created");
            }

            var questionnaire = m_questionnaireQueryService.GetModel(
                questionnaireDefinitionReference.Id);

            Questionnaire = new QuestionnaireWrapper(questionnaire);
        }
    }
}
