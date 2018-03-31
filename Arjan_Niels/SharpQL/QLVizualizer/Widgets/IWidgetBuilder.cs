using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Widgets
{
    public interface IWidgetBuilder
    {       
        void ApplyParentStyle(params QLSStyle[] styles);

        ElementManager GetElementManager();
    }
}
