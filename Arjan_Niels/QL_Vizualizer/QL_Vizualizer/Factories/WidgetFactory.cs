using QL_Parser.AST.Nodes;
using QL_Vizualizer.Widgets;
using QL_Vizualizer.Widgets.Types;
using System;
using System.Collections.Generic;

namespace QL_Vizualizer.Factories
{
    public static class WidgetFactory
    {
        public static QLWidget CreateWidget(QuestionNode node)
        {
            switch(node.ValueType)
            {
                case QValueType.BOOLEAN:
                    return new QLWidgetBool(node.ID, node.Question);
                case QValueType.INTEGER:
                    return new QLWidgetInt(node.ID, node.Question);
                case QValueType.TEXT:
                    return new QLWidgetString(node.ID, node.Question);
            }
            throw new InvalidOperationException("Unsupported type: " + node.ValueType);
        }
    }
}
