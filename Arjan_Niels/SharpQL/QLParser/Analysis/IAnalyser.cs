using QLParser.AST.QL;

namespace QLParser.Analysis
{
    public interface IQLAnalyser
    {
        bool Analyse(QLNode node);
    }
}