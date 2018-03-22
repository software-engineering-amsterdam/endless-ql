﻿using QLParser.AST.QLS;
using QLParser.AST.QLS.Enums;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
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

            if (_elementManager.Style != null)
            {
                widgetType = _elementManager.Style.GetQLSWidgetSpecification().WidgetType;
                widgetOptions.AddRange(_elementManager.Style.GetQLSWidgetSpecification().WidgetTypeArguments);
            }

            // Get inputcreator
            IInputCreator<Control, double> inputCreator = null;
            switch (widgetType)
            {
                case WidgetType.DEFAULT:
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
            return inputCreator.CreateInput(_styler, widgetOptions.ToArray(), _elementManagerLeaf as MoneyQuestionManager);
        }
    }
}
