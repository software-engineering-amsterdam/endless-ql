using QLParser.AST.QLS;

namespace QLVisualizer.Widgets
{
    public interface IStyleParser
    {
        void ParseStyle(QLSStyle qlsValues, out string[] errors);
    }
}
