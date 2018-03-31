using QLParser.AST.QLS;

namespace QLParser.Analysis
{
    public interface IQLSAnalyser
    {
        bool Analyse(QLSNode node);
    }
}
