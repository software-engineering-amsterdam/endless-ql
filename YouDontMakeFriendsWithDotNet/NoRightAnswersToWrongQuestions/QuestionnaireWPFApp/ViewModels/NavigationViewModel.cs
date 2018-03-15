using System.Collections.ObjectModel;
using Prism.Events;
using QuestionaireOrchestration.Commands;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireWPFApp.ViewModels
{
    public class NavigationViewModel : INavigationViewModel
    {
        private readonly IEventAggregator m_eventAggregator;
        private readonly IModelQueryService<QuestionnaireDefinitionModel> m_questionnaireQueryService;
        private readonly ICommandBus m_commandBus;

        public ObservableCollection<ResponseNavigationItemViewModel> ResponseNavigationItems { get; set;  } 
            = new ObservableCollection<ResponseNavigationItemViewModel>();

        public ObservableCollection<DefinitionItemViewModel> DefinitionNavigationItems { get; set; } = 
            new ObservableCollection<DefinitionItemViewModel>();
        
        public NavigationViewModel(
            IEventAggregator eventAggregator,
            IModelQueryService<QuestionnaireDefinitionModel> questionnaireQueryService,
            ICommandBus commandBus)
        {
            m_eventAggregator = eventAggregator;
            m_questionnaireQueryService = questionnaireQueryService;
            m_commandBus = commandBus;
        }

        public void Load()
        {
            DefinitionNavigationItems.Clear();
            ResponseNavigationItems.Clear();

            var command = new LoadDefinitionsFromFileCommand();
            m_commandBus.Send(command);
            foreach (var questionnaire in m_questionnaireQueryService.GetAll())
            {
                ResponseNavigationItems.Add(
                    new ResponseNavigationItemViewModel(
                        questionnaire.Id,
                        questionnaire.Name,
                        m_eventAggregator));
            }
        }
    }

    public class DefinitionItemViewModel
    {
    }
}
