using System;
using System.Windows;
using System.Windows.Controls;
using QuestionnaireUI.Models;

namespace SimpleWPFApp
{
    public class TypeTemplateSelection : DataTemplateSelector
    {
        private DependencyObject m_container;
        public override DataTemplate SelectTemplate(
            object item, 
            DependencyObject container)
        {
            m_container = container;
            if (item == null)
            {
                return GetDefaultTemplate();
            }

            var question = (QuestionWrapper) item;
            if (question.QuestionType == typeof(string))
            {
                return GetStringTemplate();
            }
            if (question.QuestionType == typeof(bool))
            {
                return GetBoolTemplate();
            }
            if (question.QuestionType == typeof(decimal))
            {
                return GetDecimalTemplate();
            }
            if (question.QuestionType == typeof(int))
            {
                return GetIntTemplate();
            }
            if (question.QuestionType == typeof(DateTime))
            {
                return GetDateTemplate();
            }
            throw new ArgumentException($"Unhandled question data type in display '{question.QuestionType}'");
        }

        private DataTemplate GetDefaultTemplate()
        {
            return GetTemplate("defaultTemplate");
        }

        private DataTemplate GetDateTemplate()
        {
            return GetTemplate("dateTemplate");
        }

        private DataTemplate GetIntTemplate()
        {
            return GetTemplate("intTemplate");
        }

        private DataTemplate GetDecimalTemplate()
        {
            return GetTemplate("decimalTemplate");
        }

        private DataTemplate GetBoolTemplate()
        {
            return GetTemplate("boolTemplate");
        }

        private DataTemplate GetStringTemplate()
        {
            return GetTemplate("stringTemplate");
        }

        private DataTemplate GetTemplate(string templateName)
        {
            return ((FrameworkElement)m_container)
                .FindResource(templateName) as DataTemplate;
        }
    }
}