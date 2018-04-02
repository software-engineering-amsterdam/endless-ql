using System;
using System.Windows;
using System.Windows.Controls;
using QuestionnaireUI.Models;

namespace SimpleWPFApp
{
    public class TypeTemplateSelection : DataTemplateSelector
    {
        public override DataTemplate SelectTemplate(
            object item, 
            DependencyObject container)
        {
            var question = (QuestionWrapper) item;
            if (question == null)
            {

                return GetDefaultTemplate(container);
            }
            return question.QuestionType.GetTemplate(container);
        }
        
        private DataTemplate GetDefaultTemplate(DependencyObject container)
        {
            return ((FrameworkElement)container)
                .FindResource("DefaultTemplate") as DataTemplate;
        }
    }
}