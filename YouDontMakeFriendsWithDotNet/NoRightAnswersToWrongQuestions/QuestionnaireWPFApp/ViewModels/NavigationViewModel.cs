using System.Collections.ObjectModel;
using Prism.Events;
using QuestionaireOrchestration.Commands;
using QuestionaireOrchestration.QueryServices.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireWPFApp.ViewModels
{
    public interface INavigationViewModel
    {
        void Load();
    }

    public class NavigationViewModel : INavigationViewModel
    {
        private readonly IEventAggregator m_eventAggregator;
        private readonly IQuestionnaireQueryService m_questionnaireQueryService;
        private readonly ICommandBus m_commandBus;

        public ObservableCollection<ResponseNavigationItemViewModel> ResponseNavigationItems { get; set;  } 
            = new ObservableCollection<ResponseNavigationItemViewModel>();

        public ObservableCollection<DefinitionItemViewModel> DefinitionNavigationItems { get; set; } = 
            new ObservableCollection<DefinitionItemViewModel>();


        public NavigationViewModel(
            IEventAggregator eventAggregator,
            IQuestionnaireQueryService questionnaireQueryService,
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

            var command = new LoadQuestionnaireDefinitionsCommand();
            m_commandBus.Send(command);
            foreach (var questionnaire in m_questionnaireQueryService.GetAll())
            {
                ResponseNavigationItems.Add(
                    new ResponseNavigationItemViewModel(
                        questionnaire.Id,
                        questionnaire.DisplayValue,
                        m_eventAggregator));
            }
        }
    }

    public class DefinitionItemViewModel
    {
    }
}
