using System;
using System.Windows.Input;
using Prism.Events;
using QuestionnaireWPFApp.Commands;
using QuestionnaireWPFApp.Events;

namespace QuestionnaireWPFApp.ViewModels
{
    public class DefinitionNavigationItemViewModel
    {
        private readonly IEventAggregator m_eventAggregator;

        public Guid QuestionnaireDefinitionId { get; }
        public string DisplayName { get; }

        public DefinitionNavigationItemViewModel(
            Guid questionnaireDefinitionId,
            string questionnaireDisplayName,
            IEventAggregator eventAggregator)
        {
            QuestionnaireDefinitionId = questionnaireDefinitionId;
            DisplayName = questionnaireDisplayName;
            m_eventAggregator = eventAggregator;
            ChangeQuestionnaireDefinitionCommand = 
                new DelegateCommand(ChangeQuestionnaireDefinitionExecute);
        }

        public ICommand ChangeQuestionnaireDefinitionCommand { get; }

        private void ChangeQuestionnaireDefinitionExecute(object obj)
        {
            m_eventAggregator
                .GetEvent<ChangeQuestionnaireDefinitionEvent>()
                .Publish(QuestionnaireDefinitionId);
        }
    }
}