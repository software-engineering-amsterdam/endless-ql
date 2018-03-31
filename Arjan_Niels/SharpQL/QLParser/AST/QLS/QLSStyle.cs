using QLParser.AST.QL;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.AST.QLS
{
    public class QLSStyle : IQLSElement
    {
        public QValueType QValueType { get; private set; }
        public IList<QLSValue> StylingValues { get; private set; }
        public QLSWidgetSpecification WidgetSpecification { get; private set; }

        public QLSStyle()
        {
            this.QValueType = QValueType.Unknown;
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

        // TODO: REFACTOR
        // qlsStyle (input) is dominant!!!
        public QLSStyle CombineWith(QLSStyle qlsStyle)
        {
            QLSStyle result = new QLSStyle
            {
                QValueType = qlsStyle.QValueType,
                WidgetSpecification = qlsStyle.WidgetSpecification,

                // all unique values with qls as dominant base
                StylingValues = qlsStyle.StylingValues.Concat(StylingValues.Where(o => !qlsStyle.StylingValues.Select(a => a.StyleProperty).Contains(o.StyleProperty))).ToList()
            };

            return result;
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