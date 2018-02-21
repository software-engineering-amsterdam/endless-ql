using System;

namespace QL_Vizualizer
{
    public class Expression<T>
    {
        public Func<T> Run { get; private set; }
        public string[] WidgetIDs { get; private set; }

        public Expression(Func<T> run, params string[] widgetValues)
        {
            Run = run;
            WidgetIDs = widgetValues;
        }
    }
}
