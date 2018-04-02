using QLParser.AST.QLS.Enums;
using QLParser.Exceptions;
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
        private const string TEXTFIELD = "textfield";
        private const string COLORPICKER = "colorpicker";

        public QLSWidgetSpecification()
        {
            this.WidgetTypeArguments = new List<string>();
            this.WidgetType = WidgetType.Default;
        }

        public QLSWidgetSpecification(WidgetType widgetType, IList<string> widgetTypeArguments)
        {
            this.WidgetType = widgetType;
            this.WidgetTypeArguments = widgetTypeArguments;
        }

        public static WidgetType ParseWidgetType(string type)
        {
            switch (type)
            {
                case RADIO:
                    return WidgetType.Radio;
                case CHECKBOX:
                    return WidgetType.Checkbox;
                case SPNNER:
                    return WidgetType.Spinner;
                case TEXTFIELD:
                    return WidgetType.Textfield;
                case COLORPICKER:
                    return WidgetType.Colorpicker;
                default:
                    throw new UnknownNodeTypeException();
            }
        }
    }
}