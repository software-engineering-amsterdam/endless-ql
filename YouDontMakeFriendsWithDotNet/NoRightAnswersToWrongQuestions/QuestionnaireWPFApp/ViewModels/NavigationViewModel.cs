using System.Collections.ObjectModel;
using Prism.Events;
using QuestionaireOrchestration.API;
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

        public ObservableCollection<NavigationItemViewModel> NavigationItems { get; set;  } 
            = new ObservableCollection<NavigationItemViewModel>();

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
            NavigationItems.Clear();
            var command = new LoadQuestionnaireDefinitionsCommand();
            m_commandBus.Send(command);
            foreach (var questionnaire in m_questionnaireQueryService.GetAll())
            {
                NavigationItems.Add(
                    new NavigationItemViewModel(
                        questionnaire.Id,
                        questionnaire.DisplayValue,
                        m_eventAggregator));
            }
        }
    }
}
