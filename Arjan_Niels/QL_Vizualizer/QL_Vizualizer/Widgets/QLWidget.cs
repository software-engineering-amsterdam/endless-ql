using System;

namespace QL_Vizualizer.Widgets
{
    public abstract class QLWidget
    {
        public string Identifyer { get; private set; }

        public QLWidget(string identifyer)
        {
            Identifyer = identifyer;
        }

    }
}
