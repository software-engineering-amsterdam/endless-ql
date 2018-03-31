using QLParser.AST.QL;
using QLParser.AST.QLS.Enums;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Leaf;
using QLVisualizer.Widgets.Windows.Leaf.InputCreators;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class MoneyBuilderWindows : WidgetLeafBuilderWindows<MoneyQuestionManager>
    {
        public MoneyBuilderWindows(MoneyQuestionManager elementManagerLeaf) : base(elementManagerLeaf)
        {
        }

        public override Control Create()
        {
            // Initialize inputcreator options
            WidgetType widgetType = WidgetType.DEFAULT;
            List<string> widgetOptions = new List<string>() { _elementManager.Text };

            if (_elementManager.GetStyle() != null)
            {
                widgetType = _elementManager.GetStyle().GetQLSWidgetSpecification().WidgetType;
                widgetOptions.AddRange(_elementManager.GetStyle().GetQLSWidgetSpecification().WidgetTypeArguments);
            }

            // Get inputcreator
            IInputCreator<Control, double> inputCreator = null;
            switch (widgetType)
            {
                case WidgetType.DEFAULT:
                case WidgetType.SPINNER:
                    inputCreator = new SpinnerCreator<double>();
                    break;
                case WidgetType.TEXTFIELD:
                    inputCreator = new TextBoxCreator<double>();
                    break;

                case WidgetType.RADIO:
                    inputCreator = new RadioButtonCreator<double>();
                    break;

                case WidgetType.CHECKBOX:
                default:
                    throw new NotImplementedException();
            }

            // Return created input
            return inputCreator.CreateInput(_styler, widgetOptions.ToArray(), _elementManager as MoneyQuestionManager);
        }

        protected override QValueType GetQValueType()
        {
            return QValueType.MONEY;
        }
    }
}
