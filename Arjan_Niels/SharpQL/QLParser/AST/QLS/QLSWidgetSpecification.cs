using QLParser.AST.QLS.Enums;
using System;
using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSWidgetSpecification
    {
        public WidgetType WidgetType { get; set; }
        public IList<string> WidgetTypeArguments { get; set; }

        private const string RADIO = "radio";
        private const string SPNNER = "spinner";
        private const string CHECKBOX = "checkbox";

        public QLSWidgetSpecification(WidgetType widgetType, IList<string> widgetTypeArguments)
        {
            this.WidgetType = WidgetType;
            this.WidgetTypeArguments = widgetTypeArguments;
        }

        public static WidgetType ParseWidgetType(string type)
        {
            switch (type)
            {
                case RADIO:
                    return WidgetType.RADIO;
                case CHECKBOX:
                    return WidgetType.CHECKBOX;
                case SPNNER:
                    return WidgetType.SPINNER;
                default:
                    throw new NotImplementedException();
            }
        }
    }
}