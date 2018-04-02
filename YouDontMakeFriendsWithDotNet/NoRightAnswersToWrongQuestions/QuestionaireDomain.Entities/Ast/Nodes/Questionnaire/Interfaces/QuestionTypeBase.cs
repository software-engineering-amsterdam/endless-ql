using System.Windows;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public abstract class QuestionTypeBase 
    {
        protected DataTemplate GetTemplate(
            DependencyObject container,
            string templateName)
        {
            return ((FrameworkElement)container)
                .FindResource(templateName) as DataTemplate;
        }
    }
}