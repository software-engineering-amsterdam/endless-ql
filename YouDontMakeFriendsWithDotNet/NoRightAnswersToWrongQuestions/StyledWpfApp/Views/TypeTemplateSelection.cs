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
            var temp = question.Model.StyleModel.Widget.GetType();
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

            if (question.Model.QuestionType == typeof(string))
            {
                return GetStringTemplate();
            }
            if (question.Model.QuestionType == typeof(bool))
            {
                return GetBoolTemplate();
            }
            if (question.Model.QuestionType == typeof(decimal))
            {
                return GetDecimalTemplate();
            }
            if (question.Model.QuestionType == typeof(int))
            {
                return GetIntTemplate();
            }
            if (question.Model.QuestionType == typeof(DateTime))
            {
                return GetDateTemplate();
            }
            throw new ArgumentException($"Unhandled question data type in display '{question.Model.QuestionType}'");
        }

        private DataTemplate GetDefaultTemplate()
        {
            return GetTemplate("DefaultTemplate");
        }

        private DataTemplate GetDateTemplate()
        {
            return GetTemplate("DateTemplate");
        }

        private DataTemplate GetIntTemplate()
        {
            return GetTemplate("IntTemplate");
        }

        private DataTemplate GetDecimalTemplate()
        {
            return GetTemplate("DecimalTemplate");
        }

        private DataTemplate GetBoolTemplate()
        {
            return GetTemplate("BoolTemplate");
        }

        private DataTemplate GetStringTemplate()
        {
            return GetTemplate("StringTemplate");
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