using System;
using System.Collections.ObjectModel;
using System.Windows.Input;
using Prism.Events;
using QuestionaireOrchestration.API;
using QuestionnaireInfrastructure.API;
using QuestionnaireWPFApp.Commands;
using QuestionnaireWPFApp.Events;

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

        public ObservableCollection<NavigationItemViewModel> NavigationItems { get; set; }

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
            //foreach (var questionnaire in m_questionnaireQueryService.GetAll())
            //{
            //    NavigationItems.Add(
            //        new NavigationItemViewModel(
            //            questionnaire.Id,
            //            questionnaire.DisplayValue,
            //            m_eventAggregator));
            //}
        }
    }

    public class NavigationItemViewModel
    {
        private readonly IEventAggregator m_eventAggregator;
        private string m_displayValue;

        public Guid QuestionnaireId { get; }
        public string DisplayValue { get; }

        public NavigationItemViewModel(
            Guid questionnaireId, 
            string questionnaireDisplayValue, 
            IEventAggregator eventAggregator)
        {
            QuestionnaireId = questionnaireId;
            DisplayValue = questionnaireDisplayValue;
            m_eventAggregator = eventAggregator;
            OpenQuestionnaireEditViewCommand = new DelegateCommand(OpenQuestionnaireEditViewExecute);

        }

        public ICommand OpenQuestionnaireEditViewCommand { get; set; }

        private void OpenQuestionnaireEditViewExecute(object obj)
        {
            m_eventAggregator
                .GetEvent<OpenQuestionnaireEditViewEvent>()
                .Publish(QuestionnaireId);
        }
    }
}
