
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
        private string m_displayValue;

        public Guid QuestionnaireId { get; }
        public string DisplayValue { get; }

        public DefinitionNavigationItemViewModel(
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