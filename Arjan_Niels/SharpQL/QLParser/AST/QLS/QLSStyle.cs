using QLParser.AST.QL;
using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSStyle : IQLSElement
    {
        public QValueType QValueType { get; private set; }
        public IList<QLSValue> StylingValues { get; private set; }
        public QLSWidgetSpecification WidgetSpecification { get; private set; }

        public QLSStyle()
        {
            this.QValueType = QValueType.UNKNOWN;
            this.StylingValues = new List<QLSValue>();
            this.WidgetSpecification = new QLSWidgetSpecification();
        }

        public QLSStyle(QValueType type) : this()
        {
            this.QValueType = type;
        }

        public QLSStyle(QValueType type, QLSWidgetSpecification specification) : this(type)
        {
            this.WidgetSpecification = specification;
        }

        public void AddStyleValue(QLSValue styleValue)
        {
            this.StylingValues.Add(styleValue);
        }

        public override string ToString()
        {
            return WidgetSpecification != null ? " - Node has a specification" : "";
        }

        #region IQLSElement
        public QLSWidgetSpecification GetQLSWidgetSpecification()
        {
            return this.WidgetSpecification;
        }

        public IList<QLSValue> GetStylingValues()
        {
            return this.StylingValues;
        }
        #endregion
    }
}