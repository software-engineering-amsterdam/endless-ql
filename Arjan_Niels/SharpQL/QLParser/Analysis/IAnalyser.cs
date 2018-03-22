using QLParser.AST.Nodes;

namespace QLParser.Analysis
{
    public interface IQLAnalyser
    {
        bool Analyse(QLNode node);
    }
}