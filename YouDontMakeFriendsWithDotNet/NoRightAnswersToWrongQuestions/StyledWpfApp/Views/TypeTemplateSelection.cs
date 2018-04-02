using System;
using System.Windows;
using System.Windows.Controls;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.UI.Models;
using QuestionnaireUI.Models;

namespace StyledWpfApp.Views
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

            var question = (StyledQuestionWrapper) item;
            if (question.Model.StyleModel.Widget.GetType() == typeof(AstRadioButton))
            {
                return GetRadioTemplate();
            }

            if (question.Model.StyleModel.Widget.GetType() == typeof(AstDropDown))
            {
                return GetComboTemplate();
            }

            if (question.Model.StyleModel.Widget.GetType() == typeof(AstTextBox))
            {
                return GetTextboxTemplate();
            }

            if (question.Model.StyleModel.Widget.GetType() == typeof(AstCheckBox))
            {
                return GetCheckboxTemplate();
            }

            if (question.Model.StyleModel.Widget.GetType() == typeof(AstSlider))
            {
                return GetTrackbarTemplate();
            }

            if (question.Model.StyleModel.Widget.GetType() == typeof(AstSpinBox))
            {
                return GetNumericUpdownTemplate();
            }

            return question.Model.QuestionType.GetTemplate(container);
        }

        private DataTemplate GetDefaultTemplate()
        {
            return GetTemplate("DefaultTemplate");
        }
        
        private DataTemplate GetRadioTemplate()
        {
            return GetTemplate("RadioTemplate");
        }

        private DataTemplate GetComboTemplate()
        {
            return GetTemplate("ComboTemplate");
        }

        private DataTemplate GetCheckboxTemplate()
        {
            return GetTemplate("CheckboxTemplate");
        }
        private DataTemplate GetTextboxTemplate()
        {
            return GetTemplate("TextboxTemplate");
        }

        private DataTemplate GetTrackbarTemplate()
        {
            return GetTemplate("TrackbarTemplate");
        }

        private DataTemplate GetNumericUpdownTemplate()
        {
            return GetTemplate("NumericUpdownTemplate");
        }
        
        private DataTemplate GetTemplate(string templateName)
        {
            return ((FrameworkElement)m_container)
                .FindResource(templateName) as DataTemplate;
        }
    }
}