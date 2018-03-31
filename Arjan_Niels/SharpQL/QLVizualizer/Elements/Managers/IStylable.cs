using QLParser.AST.QLS;

namespace QLVisualizer.Elements.Managers
{
    public interface IStylable
    {
        void SetStyle(QLSStyle style);
        QLSStyle GetStyle();
    }
}
