using System.ComponentModel;
using System.Linq;
using QuestionaireOrchestration.Commands;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices.Interfaces;
using QuestionnaireInfrastructure.API;
using SimpleWPFApp.Properties;

namespace SimpleWPFApp
{
    internal class QuestionnaireViewModel : IQuestionnaireViewModel
    {
        private readonly ICommandBus m_commandBus;
        private readonly IModelQueryService<QuestionnaireDefinitionModel> m_queryService;

        public QuestionnaireViewModel(
            ICommandBus commandBus,
            IModelQueryService<QuestionnaireDefinitionModel> queryService)
        {
            m_commandBus = commandBus;
            m_queryService = queryService;
        }

        public void Load()
        {
            var command = new CreateDefinitionFromTextCommand
            {
                DefinitionText = Resources.ExampleForm
            };

            var questionnaireDefinitionModel = m_queryService.GetAll().FirstOrDefault();
            m_commandBus.Send(command);
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected void RaisePropertyChanged(
            string propertyName = null)
        {
            PropertyChanged?.Invoke(
                this,
                new PropertyChangedEventArgs(propertyName));
        }
    }
}
