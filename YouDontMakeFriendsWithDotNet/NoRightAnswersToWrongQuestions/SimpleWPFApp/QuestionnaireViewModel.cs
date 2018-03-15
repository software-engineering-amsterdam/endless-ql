using System;
using System.ComponentModel;
using System.Linq;
using QuestionaireOrchestration.Commands;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices;
using QuestionnaireInfrastructure.API;
using QuestionnaireUI.Models;
using SimpleWPFApp.Properties;

namespace SimpleWPFApp
{
    internal class QuestionnaireViewModel : Observable, IQuestionnaireViewModel
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
        }

        public void Load()
        {
            var command = new CreateDefinitionFromTextCommand
            {
                DefinitionText = Resources.ExampleForm
            };

            m_commandBus.Send(command);
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
